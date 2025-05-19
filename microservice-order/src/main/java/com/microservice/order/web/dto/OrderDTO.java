package com.microservice.order.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        Long userId,
        Long addressId,
        LocalDateTime createdAt,
        String status,
        Double totalPrice,
        List<OrderItemDTO> items
) {}