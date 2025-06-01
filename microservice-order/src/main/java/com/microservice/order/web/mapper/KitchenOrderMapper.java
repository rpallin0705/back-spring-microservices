package com.microservice.order.web.mapper;

import com.microservice.order.domain.model.Order;
import com.microservice.order.web.dto.KitchenOrderDTO;
import com.microservice.order.web.dto.KitchenOrderItemDTO;
import com.microservice.order.web.dto.MenuDTO;
import com.microservice.order.web.dto.ProductDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KitchenOrderMapper {

    public static KitchenOrderDTO toDto(Order order, Map<Long, ProductDTO> productMap, Map<Long, MenuDTO> menuMap) {
        List<KitchenOrderItemDTO> items = order.getItems().stream().map(item -> {
            if (item.getProductId() != null) {
                ProductDTO product = productMap.get(item.getProductId());
                return new KitchenOrderItemDTO(product.name(), item.getQuantity(), false);
            } else if (item.getMenuId() != null) {
                MenuDTO menu = menuMap.get(item.getMenuId());
                return new KitchenOrderItemDTO(menu.name(), item.getQuantity(), true);
            } else {
                return new KitchenOrderItemDTO("Desconocido", item.getQuantity(), false);
            }
        }).collect(Collectors.toList());

        return new KitchenOrderDTO(order.getId(), order.getStatus(), items);
    }
}