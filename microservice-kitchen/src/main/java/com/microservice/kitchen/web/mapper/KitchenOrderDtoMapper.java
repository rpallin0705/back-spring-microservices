package com.microservice.kitchen.web.mapper;

import com.microservice.kitchen.domain.model.KitchenOrder;
import com.microservice.kitchen.web.dto.KitchenItemDTO;
import com.microservice.kitchen.web.dto.KitchenOrderDTO;

import java.util.List;
import java.util.stream.Collectors;

public class KitchenOrderDtoMapper {

    public static KitchenOrderDTO toDto(KitchenOrder order) {
        List<KitchenItemDTO> items = order.getItems().stream()
                .map(i -> new KitchenItemDTO(i.getName(), i.getQuantity()))
                .collect(Collectors.toList());

        return new KitchenOrderDTO(
                order.getId(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getDeviceId(),
                items
        );
    }
}