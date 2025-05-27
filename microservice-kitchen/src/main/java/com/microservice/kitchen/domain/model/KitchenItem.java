package com.microservice.kitchen.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KitchenItem {
    private String name;
    private int quantity;
}