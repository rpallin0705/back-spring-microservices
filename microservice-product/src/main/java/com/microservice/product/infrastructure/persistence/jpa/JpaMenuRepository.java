package com.microservice.product.infrastructure.persistence.jpa;

import com.microservice.product.application.mapper.MenuMapper;
import com.microservice.product.domain.model.Menu;
import com.microservice.product.domain.repository.MenuRepository;
import com.microservice.product.infrastructure.persistence.entity.MenuEntity;
import com.microservice.product.infrastructure.persistence.springdata.SpringDataMenuRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaMenuRepository implements MenuRepository {

    private final SpringDataMenuRepository springDataMenuRepository;

    public JpaMenuRepository(SpringDataMenuRepository springDataMenuRepository) {
        this.springDataMenuRepository = springDataMenuRepository;
    }

    @Override
    public List<Menu> findAll() {
        return springDataMenuRepository.findAll().stream()
                .map(MenuMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Menu> findById(Long id) {
        return springDataMenuRepository.findById(id)
                .map(MenuMapper::toDomain);
    }

    @Override
    public Menu save(Menu menu) {
        MenuEntity entity = MenuMapper.toEntity(menu);
        return MenuMapper.toDomain(springDataMenuRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        springDataMenuRepository.deleteById(id);
    }
}