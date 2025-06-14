package com.microservice.product.web.mapper;

import com.microservice.product.domain.model.Category;
import com.microservice.product.domain.model.Product;
import com.microservice.product.web.dto.ProductCreateDTO;
import com.microservice.product.web.dto.ProductDTO;

public class ProductDtoMapper {

    public static ProductDTO toDto(Product product) {
        if (product == null) return null;

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailable(),
                product.getCategory() != null ? product.getCategory().getName() : null
        );
    }

    public static Product toDomain(ProductDTO dto) {
        if (dto == null) return null;

        return Product.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .available(dto.available())
                .category(dto.categoryName() != null
                        ? Category.builder().name(dto.categoryName()).build()
                        : null)
                .build();
    }

    public static Product toDomain(ProductCreateDTO dto, Category category) {
        if (dto == null) return null;

        return Product.builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .available(dto.available())
                .category(category)
                .build();
    }

}
