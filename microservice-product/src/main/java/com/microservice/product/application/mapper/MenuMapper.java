package com.microservice.product.application.mapper;

import com.microservice.product.domain.model.Menu;
import com.microservice.product.domain.model.MenuProduct;
import com.microservice.product.domain.model.Product;
import com.microservice.product.infrastructure.persistence.entity.MenuEntity;
import com.microservice.product.infrastructure.persistence.entity.MenuProductEntity;
import com.microservice.product.infrastructure.persistence.entity.ProductEntity;

import java.util.List;

public class MenuMapper {

    public static Menu toDomain(MenuEntity entity) {
        if (entity == null) return null;

        List<MenuProduct> menuProducts = entity.getIncludedProducts() != null
                ? entity.getIncludedProducts().stream()
                .map(MenuMapper::toDomainMenuProduct)
                .toList()
                : List.of();

        return Menu.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .totalPrice(entity.getTotalPrice())
                .active(entity.getActive())
                .includedProducts(menuProducts)
                .build();
    }

    public static MenuEntity toEntity(Menu domain) {
        if (domain == null) return null;

        MenuEntity menuEntity = MenuEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .totalPrice(domain.getTotalPrice())
                .active(domain.getActive())
                .build();

        List<MenuProductEntity> productEntities = domain.getIncludedProducts() != null
                ? domain.getIncludedProducts().stream()
                .map(mp -> MenuProductEntity.builder()
                        .id(mp.getId())
                        .cantidad(mp.getQuantity())
                        .producto(toEntityProduct(mp.getProduct()))
                        .menu(menuEntity)
                        .build())
                .toList()
                : List.of();

        menuEntity.setIncludedProducts(productEntities);
        return menuEntity;
    }

    private static MenuProduct toDomainMenuProduct(MenuProductEntity entity) {
        if (entity == null) return null;

        return MenuProduct.builder()
                .id(entity.getId())
                .quantity(entity.getCantidad())
                .product(toDomainProduct(entity.getProducto()))
                .build();
    }

    private static Product toDomainProduct(ProductEntity entity) {
        if (entity == null) return null;

        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .available(entity.getAvailable())
                .build();
    }

    private static ProductEntity toEntityProduct(Product domain) {
        if (domain == null) return null;

        return ProductEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .price(domain.getPrice())
                .available(domain.getAvailable())
                .build();
    }
}
