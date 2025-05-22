package com.microservice.order.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        Long addressId,
        Long userId,
        String status,
        Double totalPrice,
        LocalDateTime createdAt,
        UserDetailsDTO user,
        List<OrderItemDTO> items
) {}