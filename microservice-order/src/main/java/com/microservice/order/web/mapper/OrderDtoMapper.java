package com.microservice.order.web.mapper;

import com.microservice.order.domain.model.Order;
import com.microservice.order.domain.model.OrderItem;
import com.microservice.order.web.dto.OrderDTO;
import com.microservice.order.web.dto.OrderItemDTO;
import com.microservice.order.web.dto.OrderCreateDTO;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDtoMapper {

    public static OrderDTO toDto(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getUserId(),
                order.getAddressId(),
                order.getCreatedAt(),
                order.getStatus(),
                order.getTotalPrice(),
                order.getItems().stream()
                        .map(OrderDtoMapper::toDtoItem)
                        .collect(Collectors.toList())
        );
    }

    public static Order toDomain(OrderCreateDTO dto) {
        return Order.builder()
                .userId(dto.userId())
                .addressId(dto.addressId())
                .status(dto.status())
                .totalPrice(dto.totalPrice())
                .items(dto.items().stream()
                        .map(OrderDtoMapper::toDomainItem)
                        .collect(Collectors.toList()))
                .build();
    }

    private static OrderItemDTO toDtoItem(OrderItem item) {
        return new OrderItemDTO(
                item.getProductId(),
                item.getMenuId(),
                item.getQuantity(),
                item.getPrice()
        );
    }

    private static OrderItem toDomainItem(OrderItemDTO dto) {
        return OrderItem.builder()
                .productId(dto.productId())
                .menuId(dto.menuId())
                .quantity(dto.quantity())
                .price(dto.price())
                .build();
    }
}