package com.microservice.order.web.dto;

import java.util.List;

public record OrderCreateDTO(
        Long userId,
        String deviceId,
        Long addressId,
        String status,
        List<OrderItemDTO> items
) {}