package com.microservice.order.application.service.impl;

import com.microservice.order.application.mapper.OrderStatusHistoryMapper;
import com.microservice.order.application.service.OrderService;
import com.microservice.order.domain.model.Order;
import com.microservice.order.domain.model.OrderItem;
import com.microservice.order.domain.model.OrderStatus;
import com.microservice.order.domain.model.OrderStatusHistory;
import com.microservice.order.domain.repository.OrderRepository;
import com.microservice.order.domain.repository.OrderStatusHistoryRepository;
import com.microservice.order.infrastructure.client.MenuClient;
import com.microservice.order.infrastructure.client.ProductClient;
import com.microservice.order.infrastructure.client.UserClientAdapter;
import com.microservice.order.infrastructure.websocket.OrderWebSocketNotifier;
import com.microservice.order.web.dto.*;
import com.microservice.order.web.mapper.KitchenOrderMapper;
import com.microservice.order.web.mapper.OrderDtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final OrderStatusHistoryRepository statusHistoryRepository;
    private final ProductClient productClient;
    private final MenuClient menuClient;
    private final UserClientAdapter userClientAdapter;
    private final OrderWebSocketNotifier websocketNotifier;


    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderStatusHistoryRepository statusHistoryRepository,
            ProductClient productClient,
            MenuClient menuClient,
            UserClientAdapter userClientAdapter,
            OrderWebSocketNotifier webSocketNotifer
    ) {
        this.orderRepository = orderRepository;
        this.statusHistoryRepository = statusHistoryRepository;
        this.productClient = productClient;
        this.menuClient = menuClient;
        this.userClientAdapter = userClientAdapter;
        this.websocketNotifier = webSocketNotifer;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderDTO getFullOrder(Long id) {
        Order order = getOrderById(id);

        Map<Long, ProductDTO> productMap = order.getItems().stream()
                .map(OrderItem::getProductId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toMap(pid -> pid, productClient::getProductById));

        Map<Long, MenuDTO> menuMap = order.getItems().stream()
                .map(OrderItem::getMenuId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toMap(mid -> mid, menuClient::getMenuById));

        UserDetailsDTO userDetails = order.getUserId() != null
                ? userClientAdapter.getUserDetailsIfPresent(order.getAddressId())
                : null;

        return OrderDtoMapper.toDto(order, productMap, menuMap, userDetails);
    }

    @Override
    public List<OrderDTO> getAllFullOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        String role = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_ANONYMOUS");

        log.info("\uD83D\uDCE5 Usuario autenticado: {} con rol {}", email, role);

        final Long userId = role.equals("ROLE_USER")
                ? userClientAdapter.getAuthenticatedUser().id()
                : null;
        if (userId != null) {
            log.info("\uD83D\uDD0D ID del usuario autenticado: {}", userId);
        }

        final LocalDate today = LocalDate.now();

        return orderRepository.findAll().stream()
                .filter(order -> {
                    return switch (role) {
                        case "ROLE_ADMIN" -> true;
                        case "ROLE_COOK" -> order.getCreatedAt().toLocalDate().equals(today);
                        case "ROLE_USER" -> order.getUserId() != null && order.getUserId().equals(userId);
                        default -> false;
                    };
                })
                .map(order -> {
                    Map<Long, ProductDTO> productMap = order.getItems().stream()
                            .map(OrderItem::getProductId)
                            .filter(Objects::nonNull)
                            .distinct()
                            .collect(Collectors.toMap(pid -> pid, productClient::getProductById));

                    Map<Long, MenuDTO> menuMap = order.getItems().stream()
                            .map(OrderItem::getMenuId)
                            .filter(Objects::nonNull)
                            .distinct()
                            .collect(Collectors.toMap(mid -> mid, menuClient::getMenuById));

                    UserDetailsDTO userDetails = order.getUserId() != null
                            ? userClientAdapter.getUserDetailsIfPresent(order.getAddressId())
                            : null;

                    return OrderDtoMapper.toDto(order, productMap, menuMap, userDetails);
                })
                .toList();
    }

    @Override
    public Order create(Order order) {
        order.setCreatedAt(LocalDateTime.now());

        if ((order.getUserId() == null && order.getDeviceId() == null) ||
                (order.getUserId() != null && order.getDeviceId() != null)) {
            throw new IllegalArgumentException("El pedido debe contener solo uno: userId o deviceId");
        }

        double total = 0.0;

        for (OrderItem item : order.getItems()) {
            if (item.getProductId() != null) {
                ProductDTO product = productClient.getProductById(item.getProductId());

                if (!product.available()) {
                    throw new RuntimeException("Producto no disponible: " + product.name());
                }

                item.setPrice(product.price());
                total += product.price() * item.getQuantity();
            }

            if (item.getMenuId() != null) {
                MenuDTO menu = menuClient.getMenuById(item.getMenuId());

                if (!menu.active()) {
                    throw new RuntimeException("Men\u00fa no disponible: " + menu.name());
                }

                item.setPrice(menu.totalPrice());
                total += menu.totalPrice() * item.getQuantity();
            }
        }

        order.setTotalPrice(total);
        int estimatedTime = calculateEstimatedPreparationTime(order.getItems(), order.getAddressId());
        order.setEstimatedPreparationTime(estimatedTime);

        Order saved = orderRepository.save(order);

        statusHistoryRepository.save(OrderStatusHistory.builder()
                .orderId(saved.getId())
                .status(saved.getStatus())
                .changedAt(LocalDateTime.now())
                .build());

        websocketNotifier.notifyNewOrder(saved.getId());

        return saved;
    }

    private int calculateEstimatedPreparationTime(List<OrderItem> items, Long addressId) {
        int basePerItem = ThreadLocalRandom.current().nextInt(4, 9);
        int productTime = items.size() * basePerItem;

        int deliveryExtra = (addressId != null)
                ? ThreadLocalRandom.current().nextInt(5, 11)
                : 0;

        return productTime + deliveryExtra;
    }

    @Override
    public void delete(Long id) {
        getOrderById(id);
        orderRepository.deleteById(id);
    }

    private Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
    }

    @Override
    public List<OrderStatusHistory> getStatusHistory(Long orderId) {
        getOrderById(orderId);
        return statusHistoryRepository.findByOrderId(orderId);
    }

    @Override
    public List<KitchenOrderDTO> getOrdersForKitchen() {
        return orderRepository.findAll().stream()
                .filter(order -> order.getStatus() == OrderStatus.CREATED || order.getStatus() == OrderStatus.PREPARING)
                .map(order -> {
                    Map<Long, ProductDTO> productMap = order.getItems().stream()
                            .map(OrderItem::getProductId)
                            .filter(Objects::nonNull)
                            .distinct()
                            .collect(Collectors.toMap(pid -> pid, productClient::getProductById));

                    Map<Long, MenuDTO> menuMap = order.getItems().stream()
                            .map(OrderItem::getMenuId)
                            .filter(Objects::nonNull)
                            .distinct()
                            .collect(Collectors.toMap(mid -> mid, menuClient::getMenuById));

                    return KitchenOrderMapper.toDto(order, productMap, menuMap);
                })
                .toList();
    }

    @Override
    public void updateStatus(Long orderId, OrderStatus newStatus) {
        Order order = getOrderById(orderId);
        OrderStatus current = order.getStatus();

        if (!current.canTransitionTo(newStatus)) {
            throw new IllegalArgumentException("Transici\u00f3n inv\u00e1lida de " + current + " a " + newStatus);
        }

        order.setStatus(newStatus);
        orderRepository.save(order);

        statusHistoryRepository.save(OrderStatusHistory.builder()
                .orderId(order.getId())
                .status(newStatus)
                .changedAt(LocalDateTime.now())
                .build());
    }
}