package com.microservice.kitchen.web.dto;


import com.microservice.kitchen.domain.model.OrderStatus;
import com.microservice.order.web.dto.UserDetailsDTO;

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