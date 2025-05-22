package com.microservice.user.application.mapper;

import com.microservice.user.domain.model.User;
import com.microservice.user.infrastructure.persistence.entity.UserEntity;

import java.util.stream.Collectors;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;

        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .addresses(entity.getAddresses() != null
                        ? entity.getAddresses().stream()
                        .map(AddressMapper::toDomain)
                        .collect(Collectors.toList())
                        : null)
                .build();
    }

    public static UserEntity toEntity(User domain) {
        if (domain == null) return null;

        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setEmail(domain.getEmail());
        entity.setPhone(domain.getPhone());
        entity.setRole(domain.getRole());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());

        // Relación inversa: seteamos user dentro de cada address también
        if (domain.getAddresses() != null) {
            entity.setAddresses(
                    domain.getAddresses().stream()
                            .map(address -> {
                                var entityAddress = AddressMapper.toEntity(address);
                                entityAddress.setUser(entity);
                                return entityAddress;
                            }).collect(Collectors.toList())
            );
        }

        return entity;
    }
}