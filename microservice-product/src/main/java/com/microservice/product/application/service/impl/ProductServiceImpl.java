package com.microservice.product.application.service.impl;

import com.microservice.product.application.service.ProductService;
import com.microservice.product.domain.model.Product;
import com.microservice.product.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product updatedProduct) {
        Product existing = getById(id);

        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setImageUrl(updatedProduct.getImageUrl());
        existing.setAvailable(updatedProduct.getAvailable());
        existing.setCategory(updatedProduct.getCategory());

        return productRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        getById(id); // lanza excepción si no existe
        productRepository.deleteById(id);
    }
}
