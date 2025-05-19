package com.microservice.product.domain.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {
    private Long id;
    private String name;
    private String description;
    private Double totalPrice;
    private Boolean active;
    private List<MenuProduct> includedProducts;
}