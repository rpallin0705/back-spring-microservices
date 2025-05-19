package com.microservice.order.infrastructure.client;

import com.microservice.order.web.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-product", path = "/api/products", contextId = "productClient")
public interface ProductClient {
    @GetMapping("/{id}")
    ProductDTO getProductById(@PathVariable Long id);
}