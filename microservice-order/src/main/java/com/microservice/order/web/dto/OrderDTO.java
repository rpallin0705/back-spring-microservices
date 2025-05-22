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
        UserDTO user,
        List<OrderItemDTO> items
) {}
