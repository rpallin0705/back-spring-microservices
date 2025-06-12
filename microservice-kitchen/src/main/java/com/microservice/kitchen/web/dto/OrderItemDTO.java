package com.microservice.kitchen.web.dto;

import com.microservice.order.web.dto.ProductDTO;

public record OrderItemDTO(
        Long productId,
        Long menuId,
        Integer quantity,
        Double price,
        ProductDTO product,
        MenuDTO menu
) {}