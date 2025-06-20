package com.microservice.kitchen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KitchenOrder {
    private Long id;
    private String deviceId;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private List<KitchenItem> items;
}