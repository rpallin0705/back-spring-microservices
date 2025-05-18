package com.microservice.order.web.mapper;

import com.microservice.order.domain.model.Order;
import com.microservice.order.domain.model.OrderItem;
import com.microservice.order.infrastructure.client.ProductClient;
import com.microservice.order.web.dto.OrderCreateDTO;
import com.microservice.order.web.dto.OrderDTO;
import com.microservice.order.web.dto.OrderItemDTO;
import com.microservice.order.web.dto.ProductDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDtoMapper {

    public static OrderDTO toDto(Order order, Map<Long, ProductDTO> productMap) {
        List<OrderItemDTO> itemDtos = order.getItems().stream()
                .map(item -> {
                    ProductDTO product = productMap.get(item.getProductId());
                    return new OrderItemDTO(
                            item.getProductId(),
                            item.getMenuId(),
                            item.getQuantity(),
                            item.getPrice(),
                            product
                    );
                }).toList();

        return new OrderDTO(
                order.getId(),
                order.getUserId(),
                order.getAddressId(),
                order.getCreatedAt(),
                order.getStatus(),
                order.getTotalPrice(),
                itemDtos
        );
    }


    private static OrderItemDTO toDtoItem(OrderItem item, ProductDTO productDTO) {
        return new OrderItemDTO(
                item.getProductId(),
                item.getMenuId(),
                item.getQuantity(),
                item.getPrice(),
                productDTO
        );
    }

    public static Order toDomain(OrderCreateDTO dto) {
        return Order.builder()
                .userId(dto.userId())
                .addressId(dto.addressId())
                .status(dto.status())
                .totalPrice(dto.totalPrice())
                .items(dto.items().stream()
                        .map(i -> OrderItem.builder()
                                .productId(i.productId())
                                .menuId(i.menuId())
                                .quantity(i.quantity())
                                .price(i.price())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}