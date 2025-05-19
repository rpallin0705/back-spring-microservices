package com.microservice.product.application.service.impl;

import com.microservice.product.application.service.CategoryService;
import com.microservice.product.domain.model.Category;
import com.microservice.product.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Category> getAll() {
        return repo.findAll();
    }

    @Override
    public Category getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
    }

    @Override
    public Category create(Category category) {
        return repo.save(category);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        repo.deleteById(id);
    }
}
