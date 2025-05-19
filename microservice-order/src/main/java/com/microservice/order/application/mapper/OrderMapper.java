package com.microservice.order.application.mapper;

import com.microservice.order.domain.model.Order;
import com.microservice.order.domain.model.OrderItem;
import com.microservice.order.infrastructure.persistence.entity.OrderEntity;
import com.microservice.order.infrastructure.persistence.entity.OrderItemEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static Order toDomain(OrderEntity entity) {
        if (entity == null) return null;

        return Order.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .addressId(entity.getAddressId())
                .createdAt(entity.getCreatedAt())
                .status(entity.getStatus())
                .totalPrice(entity.getTotalPrice())
                .items(toDomainList(entity.getItems()))
                .build();
    }

    public static OrderEntity toEntity(Order domain) {
        if (domain == null) return null;

        OrderEntity entity = OrderEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .addressId(domain.getAddressId())
                .createdAt(domain.getCreatedAt())
                .status(domain.getStatus())
                .totalPrice(domain.getTotalPrice())
                .build();

        if (domain.getItems() != null) {
            List<OrderItemEntity> items = toEntityList(domain.getItems());
            items.forEach(i -> i.setOrder(entity));
            entity.setItems(items);
        }

        return entity;
    }

    private static List<OrderItem> toDomainList(List<OrderItemEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(OrderItemMapper::toDomain).collect(Collectors.toList());
    }

    private static List<OrderItemEntity> toEntityList(List<OrderItem> items) {
        if (items == null) return null;
        return items.stream().map(OrderItemMapper::toEntity).collect(Collectors.toList());
    }
}