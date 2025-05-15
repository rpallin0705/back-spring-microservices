package com.microservice.product.infrastructure.persistence.jpa;

import com.microservice.product.application.mapper.CategoryMapper;
import com.microservice.product.domain.model.Category;
import com.microservice.product.domain.repository.CategoryRepository;
import com.microservice.product.infrastructure.persistence.springdata.SpringDataCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaCategoryRepository implements CategoryRepository {

    private final SpringDataCategoryRepository springRepo;

    public JpaCategoryRepository(SpringDataCategoryRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public List<Category> findAll() {
        return springRepo.findAll().stream()
                .map(CategoryMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findById(Long id) {
        return springRepo.findById(id)
                .map(CategoryMapper::toDomain);
    }

    @Override
    public Category save(Category category) {
        return CategoryMapper.toDomain(
                springRepo.save(CategoryMapper.toEntity(category))
        );
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }
}