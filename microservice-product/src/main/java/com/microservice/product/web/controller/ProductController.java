package com.microservice.product.web.controller;

import com.microservice.product.application.service.ProductService;
import com.microservice.product.domain.model.Category;
import com.microservice.product.domain.model.Product;
import com.microservice.product.web.dto.ProductCreateDTO;
import com.microservice.product.web.dto.ProductDTO;
import com.microservice.product.web.mapper.ProductDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> result = productService.getAll().stream()
                .map(ProductDtoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return ResponseEntity.ok(ProductDtoMapper.toDto(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductCreateDTO dto) {
        Category category = Category.builder().id(dto.categoryId()).build();
        Product product = ProductDtoMapper.toDomain(dto, category);
        Product created = productService.create(product);
        return ResponseEntity.ok(ProductDtoMapper.toDto(created));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductCreateDTO dto) {
        Category category = Category.builder().id(dto.categoryId()).build();
        Product product = ProductDtoMapper.toDomain(dto, category);
        Product updated = productService.update(id, product);
        return ResponseEntity.ok(ProductDtoMapper.toDto(updated));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}