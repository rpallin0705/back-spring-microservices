package com.microservice.product.application.service;

import com.microservice.product.domain.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category getById(Long id);
    Category create(Category category);
    void delete(Long id);
}