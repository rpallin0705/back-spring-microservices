package com.microservice.order.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderStatusHistory {
    private Long id;
    private Long orderId;
    private OrderStatus status;
    private LocalDateTime changedAt;
}