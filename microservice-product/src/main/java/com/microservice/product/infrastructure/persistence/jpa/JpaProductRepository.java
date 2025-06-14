package com.microservice.product.infrastructure.persistence.jpa;

import com.microservice.product.application.mapper.ProductMapper;
import com.microservice.product.domain.model.Product;
import com.microservice.product.domain.repository.ProductRepository;
import com.microservice.product.infrastructure.persistence.entity.ProductEntity;
import com.microservice.product.infrastructure.persistence.springdata.SpringDataProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaProductRepository implements ProductRepository {

    private final SpringDataProductRepository springDataRepository;

    public JpaProductRepository(SpringDataProductRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return springDataRepository.findById(id)
                .map(ProductMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return springDataRepository.findAll().stream()
                .map(ProductMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);
        ProductEntity saved = springDataRepository.save(entity);
        return ProductMapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        springDataRepository.deleteById(id);
    }
}
