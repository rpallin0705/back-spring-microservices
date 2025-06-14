package com.microservice.order.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Order {
    private Long id;
    private Long userId;
    private String deviceId;
    private Long addressId;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private Double totalPrice;
    private List<OrderItem> items;

    private Integer estimatedPreparationTime;
}