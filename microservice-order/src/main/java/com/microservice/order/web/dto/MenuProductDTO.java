package com.microservice.order.web.dto;

public record MenuProductDTO(
        Long id,
        Long productId,
        String productName,
        Integer quantity
) {}