package com.microservice.order.web.dto;

import java.time.LocalDateTime;

public record OrderStatusHistoryDTO(
        String status,
        LocalDateTime changedAt
) {}