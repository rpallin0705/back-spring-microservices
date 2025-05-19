package com.microservice.product.infrastructure.persistence.springdata;

import com.microservice.product.infrastructure.persistence.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMenuRepository extends JpaRepository<MenuEntity, Long> {
}