package com.microservice.product.application.service;

import com.microservice.product.domain.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product getById(Long id);

    Product create(Product product);

    Product update(Long id, Product updatedProduct);

    void delete(Long id);
}
