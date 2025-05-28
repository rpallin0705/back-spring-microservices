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
import com.microservice.order.infrastructure.client.UserClient;
import com.microservice.order.web.dto.*;
import com.microservice.order.web.mapper.KitchenOrderMapper;
import com.microservice.order.web.mapper.OrderDtoMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusHistoryRepository statusHistoryRepository;
    private final ProductClient productClient;
    private final MenuClient menuClient;
    private final UserClient userClient;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderStatusHistoryRepository statusHistoryRepository,
            ProductClient productClient,
            MenuClient menuClient,
            UserClient userClient // <-- nuevo
    ) {
        this.orderRepository = orderRepository;
        this.statusHistoryRepository = statusHistoryRepository;
        this.productClient = productClient;
        this.menuClient = menuClient;
        this.userClient = userClient;
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

        UserDetailsDTO userDetails = userClient.getUserDetailsForOrder(order.getUserId(), order.getAddressId());

        return OrderDtoMapper.toDto(order, productMap, menuMap, userDetails);
    }

    @Override
    public List<OrderDTO> getAllFullOrders() {
        return orderRepository.findAll().stream()
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

                    UserDetailsDTO userDetails = userClient.getUserDetailsForOrder(order.getUserId(), order.getAddressId());

                    return OrderDtoMapper.toDto(order, productMap, menuMap, userDetails);
                })
                .toList();
    }

    @Override
    public Order create(Order order) {
        order.setCreatedAt(LocalDateTime.now());

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
                    throw new RuntimeException("Menú no disponible: " + menu.name());
                }

                item.setPrice(menu.totalPrice());
                total += menu.totalPrice() * item.getQuantity();
            }
        }

        order.setTotalPrice(total);
        Order saved = orderRepository.save(order);

        statusHistoryRepository.save(OrderStatusHistory.builder()
                .orderId(saved.getId())
                .status(saved.getStatus())
                .changedAt(LocalDateTime.now())
                .build());

        return saved;
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
        getOrderById(orderId); // asegura que existe y lanza si no
        return statusHistoryRepository.findByOrderId(orderId);
    }

    ///
    /// SERVICIO KITCHEN
    ///

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
        OrderStatus current = order.getStatus(); // Ya es enum

        if (!current.canTransitionTo(newStatus)) {
            throw new IllegalArgumentException("Transición inválida de " + current + " a " + newStatus);
        }

        order.setStatus(newStatus);
        orderRepository.save(order);

        statusHistoryRepository.save(OrderStatusHistory.builder()
                .orderId(order.getId())
                .status(newStatus) // Enum directamente
                .changedAt(LocalDateTime.now())
                .build());
    }
}