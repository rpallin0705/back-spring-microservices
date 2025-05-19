package com.microservice.order.web.dto;

public record ProductDTO(
        Long id,
        String name,
        String description,
        Double price,
        Boolean available,
        String categoryName
) {}
