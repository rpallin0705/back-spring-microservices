package com.microservice.kitchen.web.dto;

import com.microservice.kitchen.domain.model.OrderStatus;

import java.time.LocalDateTime;

public record OrderStatusHistoryDTO(
        OrderStatus status,
        LocalDateTime changedAt
) {}