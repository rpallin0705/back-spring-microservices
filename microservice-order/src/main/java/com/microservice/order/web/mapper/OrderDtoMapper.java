package com.microservice.order.web.mapper;

import com.microservice.order.domain.model.Order;
import com.microservice.order.domain.model.OrderItem;
import com.microservice.order.domain.model.OrderStatus;
import com.microservice.order.web.dto.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDtoMapper {

    public static OrderDTO toDto(Order order, Map<Long, ProductDTO> productMap, Map<Long, MenuDTO> menuMap, UserDetailsDTO userDetails) {
        List<OrderItemDTO> itemsDto = order.getItems().stream()
                .map(item -> {
                    ProductDTO product = productMap.get(item.getProductId());
                    MenuDTO menu = menuMap.get(item.getMenuId());
                    return new OrderItemDTO(
                            item.getProductId(),
                            item.getMenuId(),
                            item.getQuantity(),
                            item.getPrice(),
                            product,
                            menu
                    );
                })
                .toList();

        return new OrderDTO(
                order.getId(),
                order.getAddressId(),
                order.getUserId(),
                order.getDeviceId(), // ← añadido
                order.getStatus(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                order.getEstimatedPreparationTime(),
                userDetails,
                itemsDto
        );
    }

    public static OrderDTO toDto(Order order, Map<Long, ProductDTO> productMap, Map<Long, MenuDTO> menuMap) {
        return toDto(order, productMap, menuMap, null);
    }

    public static Order toDomain(OrderCreateDTO dto) {
        return Order.builder()
                .userId(dto.userId())
                .deviceId(dto.deviceId()) // ← añadido
                .addressId(dto.addressId())
                .status(OrderStatus.valueOf(dto.status()))
                .items(dto.items().stream()
                        .map(i -> OrderItem.builder()
                                .productId(i.productId())
                                .menuId(i.menuId())
                                .quantity(i.quantity())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}