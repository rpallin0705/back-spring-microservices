package com.microservice.kitchen.application.mapper;

import com.microservice.kitchen.domain.model.KitchenItem;
import com.microservice.kitchen.web.dto.KitchenItemDTO;

public class KitchenItemMapper {

    public static KitchenItem toDomain(KitchenItemDTO dto) {
        return KitchenItem.builder()
                .name(dto.name())
                .quantity(dto.quantity())
                .build();
    }

    public static KitchenItemDTO toDto(KitchenItem domain) {
        return new KitchenItemDTO(
                domain.getName(),
                domain.getQuantity()
        );
    }
}