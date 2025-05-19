package com.microservice.product.web.mapper;

import com.microservice.product.domain.model.Menu;
import com.microservice.product.domain.model.MenuProduct;
import com.microservice.product.domain.model.Product;
import com.microservice.product.web.dto.MenuDTO;
import com.microservice.product.web.dto.MenuProductDTO;

import java.util.List;

public class MenuDtoMapper {

    public static MenuDTO toDto(Menu menu) {
        List<MenuProductDTO> items = menu.getIncludedProducts().stream()
                .map(mp -> new MenuProductDTO(
                        mp.getId(),
                        mp.getProduct().getId(),
                        mp.getProduct().getName(),
                        mp.getQuantity()
                ))
                .toList();

        return new MenuDTO(
                menu.getId(),
                menu.getName(),
                menu.getDescription(),
                menu.getTotalPrice(),
                menu.getActive(),
                items
        );
    }

    public static Menu toDomain(MenuDTO dto) {
        List<MenuProduct> products = dto.products().stream()
                .map(mp -> MenuProduct.builder()
                        .id(mp.id())
                        .quantity(mp.quantity())
                        .product(Product.builder()
                                .id(mp.productId())
                                .name(mp.productName())
                                .build())
                        .build())
                .toList();

        return Menu.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .totalPrice(dto.totalPrice())
                .active(dto.active())
                .includedProducts(products)
                .build();
    }
}
