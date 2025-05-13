package com.microservice.product.domain.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuProduct {
    private Long id;
    private Product product;
    private Integer quantity;
}