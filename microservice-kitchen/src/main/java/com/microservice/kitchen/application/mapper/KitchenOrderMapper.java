package com.microservice.kitchen.application.mapper;

import com.microservice.kitchen.domain.model.KitchenItem;
import com.microservice.kitchen.domain.model.KitchenOrder;
import com.microservice.kitchen.domain.model.OrderStatus;
import com.microservice.kitchen.web.dto.KitchenItemDTO;
import com.microservice.kitchen.web.dto.KitchenOrderDTO;

import java.util.List;
import java.util.stream.Collectors;

public class KitchenOrderMapper {

    public static KitchenOrder toDomain(KitchenOrderDTO dto) {
        List<KitchenItem> items = dto.items().stream()
                .map(KitchenItemMapper::toDomain)
                .collect(Collectors.toList());

        return KitchenOrder.builder()
                .id(dto.id())
                .status(dto.status())
                .createdAt(dto.createdAt())
                .deviceId(dto.deviceId())
                .items(items)
                .build();
    }

    public static KitchenOrderDTO toDto(KitchenOrder domain) {
        List<KitchenItemDTO> items = domain.getItems().stream()
                .map(KitchenItemMapper::toDto)
                .collect(Collectors.toList());

        return new KitchenOrderDTO(
                domain.getId(),
                domain.getStatus(),
                domain.getCreatedAt(),
                domain.getDeviceId(),
                items
        );
    }

    public static KitchenOrder fromStatusUpdate(Long orderId, OrderStatus status) {
        KitchenOrder order = new KitchenOrder();
        order.setId(orderId);
        order.setStatus(status);
        return order;
    }
}