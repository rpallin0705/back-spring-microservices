package com.microservice.product.web.mapper;

import com.microservice.product.domain.model.Category;
import com.microservice.product.web.dto.CategoryDTO;

public class CategoryDtoMapper {

    public static CategoryDTO toDto(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
    }
}