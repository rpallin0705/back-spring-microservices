package com.microservice.product.web.dto;

public record ProductCreateDTO(
        String name,
        String description,
        Double price,
        Boolean available,
        Long categoryId
) {}
