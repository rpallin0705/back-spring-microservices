package com.microservice.order.web.dto;

import java.util.List;

public record KitchenOrderDTO(
        Long orderId,
        com.microservice.order.domain.model.OrderStatus status,
        List<KitchenOrderItemDTO> items
) {}