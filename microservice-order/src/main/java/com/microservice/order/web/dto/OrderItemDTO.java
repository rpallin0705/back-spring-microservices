package com.microservice.order.web.dto;

public record OrderItemDTO(
        Long productId,
        Long menuId,
        Integer quantity,
        Double price,
        ProductDTO product
) {}
