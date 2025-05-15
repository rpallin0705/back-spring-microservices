package com.microservice.order.application.mapper;

import com.microservice.order.domain.model.OrderStatusHistory;
import com.microservice.order.infrastructure.persistence.entity.OrderStatusHistoryEntity;

public class OrderStatusHistoryMapper {

    public static OrderStatusHistory toDomain(OrderStatusHistoryEntity entity) {
        if (entity == null) return null;

        return OrderStatusHistory.builder()
                .id(entity.getId())
                .orderId(entity.getOrder().getId())
                .status(entity.getStatus())
                .changedAt(entity.getChangedAt())
                .build();
    }

    public static OrderStatusHistoryEntity toEntity(OrderStatusHistory domain) {
        if (domain == null) return null;

        return OrderStatusHistoryEntity.builder()
                .id(domain.getId())
                .status(domain.getStatus())
                .changedAt(domain.getChangedAt())
                .build();
    }
}