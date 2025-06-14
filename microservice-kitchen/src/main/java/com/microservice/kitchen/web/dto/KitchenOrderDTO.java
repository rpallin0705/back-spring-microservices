package com.microservice.kitchen.web.dto;

import com.microservice.kitchen.domain.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record KitchenOrderDTO(
        Long id,
        OrderStatus status,
        LocalDateTime createdAt,
        String deviceId,
        List<KitchenItemDTO> items
) {}