package com.microservice.auth.application.mapper;

import com.microservice.auth.domain.model.User;
import com.microservice.auth.infrastructure.persistence.entity.UserEntity;

import java.util.stream.Collectors;

public class UserMapper {
    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRoles().stream().map(RoleMapper::toDomain).collect(Collectors.toSet())
        );
    }

    public static UserEntity toEntity(User domain) {
        return UserEntity.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .roles(domain.getRoles().stream().map(RoleMapper::toEntity).collect(Collectors.toSet()))
                .build();
    }
}
