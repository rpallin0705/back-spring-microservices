package com.microservice.order.web.dto;

import com.microservice.order.domain.model.OrderStatus;

import java.time.LocalDateTime;

public record OrderStatusHistoryDTO(
        OrderStatus status,
        LocalDateTime changedAt
) {}