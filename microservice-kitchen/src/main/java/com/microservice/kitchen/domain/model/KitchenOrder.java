package com.microservice.kitchen.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class KitchenOrder {
    private Long id;
    private String status;
    private LocalDateTime createdAt;
    private List<KitchenItem> items;
}