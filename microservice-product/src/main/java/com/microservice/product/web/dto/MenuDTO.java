package com.microservice.product.web.dto;

import java.util.List;

public record MenuDTO(
        Long id,
        String name,
        String description,
        Double totalPrice,
        Boolean active,
        List<MenuProductDTO> products
) {}