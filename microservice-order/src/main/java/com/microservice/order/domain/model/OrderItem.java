package com.microservice.order.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem {
    private Long id;
    private Long orderId;
    private Long productId;
    private Long menuId;
    private Integer quantity;
    private Double price;
}