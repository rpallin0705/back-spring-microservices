package com.microservice.kitchen.web.dto;

public record MenuProductDTO(
        Long id,
        Long productId,
        String productName,
        Integer quantity
) {}