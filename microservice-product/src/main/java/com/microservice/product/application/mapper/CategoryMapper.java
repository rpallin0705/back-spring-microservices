package com.microservice.product.application.mapper;

import com.microservice.product.domain.model.Category;
import com.microservice.product.infrastructure.persistence.entity.CategoryEntity;

public class CategoryMapper {

    public static Category toDomain(CategoryEntity entity) {
        return Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public static CategoryEntity toEntity(Category category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
