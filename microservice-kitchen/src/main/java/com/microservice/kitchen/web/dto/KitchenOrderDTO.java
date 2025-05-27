package com.microservice.kitchen.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record KitchenOrderDTO(
        Long id,
        String status,
        LocalDateTime createdAt,
        List<KitchenItemDTO> items
) {}