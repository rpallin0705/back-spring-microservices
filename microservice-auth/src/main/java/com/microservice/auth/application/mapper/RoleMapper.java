package com.microservice.auth.application.mapper;

import com.microservice.auth.domain.model.Role;
import com.microservice.auth.infrastructure.persistence.entity.RoleEntity;

public class RoleMapper {
    public static Role toDomain(RoleEntity entity) {
        return new Role(entity.getId(), entity.getName());
    }

    public static RoleEntity toEntity(Role domain) {
        return RoleEntity.builder()
                .id(domain.id())
                .name(domain.name())
                .build();
    }
}