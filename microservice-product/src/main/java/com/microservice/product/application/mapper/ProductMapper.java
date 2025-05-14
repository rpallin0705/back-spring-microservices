package com.microservice.product.application.mapper;

import com.microservice.product.domain.model.Category;
import com.microservice.product.domain.model.Product;
import com.microservice.product.infrastructure.persistence.entity.CategoryEntity;
import com.microservice.product.infrastructure.persistence.entity.ProductEntity;

public class ProductMapper {

    public static Product toDomain(ProductEntity entity) {
        if (entity == null) return null;

        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .available(entity.getAvailable())
                .category(toDomain(entity.getCategory()))
                .build();
    }

    public static ProductEntity toEntity(Product domain) {
        if (domain == null) return null;

        return ProductEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .price(domain.getPrice())
                .available(domain.getAvailable())
                .category(toEntity(domain.getCategory()))
                .build();
    }

    private static Category toDomain(CategoryEntity entity) {
        if (entity == null) return null;

        return Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    private static CategoryEntity toEntity(Category domain) {
        if (domain == null) return null;

        return CategoryEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .build();
    }
}
