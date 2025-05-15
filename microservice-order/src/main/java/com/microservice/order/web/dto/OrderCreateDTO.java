package com.microservice.order.web.dto;

import java.util.List;

public record OrderCreateDTO(
        Long userId,
        Long addressId,
        String status,
        Double totalPrice,
        List<OrderItemDTO> items
) {}