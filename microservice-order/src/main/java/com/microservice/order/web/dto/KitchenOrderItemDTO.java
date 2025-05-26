package com.microservice.order.web.dto;

public record KitchenOrderItemDTO(
        String name,
        int quantity,
        boolean isMenu
) {}
