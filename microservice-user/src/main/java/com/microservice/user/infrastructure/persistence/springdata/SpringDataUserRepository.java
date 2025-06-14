package com.microservice.user.infrastructure.persistence.springdata;

import com.microservice.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByAuthEmail(String authEmail);
}