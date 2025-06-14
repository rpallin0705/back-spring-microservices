package com.microservice.order.web.dto;

import com.microservice.order.domain.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        Long addressId,
        Long userId,
        String deviceId,
        OrderStatus status,
        Double totalPrice,
        LocalDateTime createdAt,
        Integer estimatedPreparationTime,
        UserDetailsDTO user,
        List<OrderItemDTO> items
) {}