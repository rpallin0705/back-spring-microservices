package com.microservice.order.application.mapper;

import com.microservice.order.domain.model.OrderItem;
import com.microservice.order.infrastructure.persistence.entity.OrderItemEntity;

public class OrderItemMapper {

    public static OrderItem toDomain(OrderItemEntity entity) {
        if (entity == null) return null;

        return OrderItem.builder()
                .id(entity.getId())
                .orderId(entity.getOrder().getId())
                .productId(entity.getProductId())
                .menuId(entity.getMenuId())
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .build();
    }

    public static OrderItemEntity toEntity(OrderItem domain) {
        if (domain == null) return null;

        return OrderItemEntity.builder()
                .id(domain.getId())
                .productId(domain.getProductId())
                .menuId(domain.getMenuId())
                .quantity(domain.getQuantity())
                .price(domain.getPrice())
                .build();
    }
}
