package com.microservice.user.application.mapper;

import com.microservice.user.domain.model.Address;
import com.microservice.user.infrastructure.persistence.entity.AddressEntity;
import com.microservice.user.infrastructure.persistence.entity.UserEntity;

public class AddressMapper {

    public static Address toDomain(AddressEntity entity) {
        if (entity == null) return null;

        return Address.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .city(entity.getCity())
                .postalCode(entity.getPostalCode())
                .country(entity.getCountry())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .build();
    }

    public static AddressEntity toEntity(Address address) {
        if (address == null) return null;

        UserEntity user = UserEntity.builder().id(address.getUserId()).build();

        return AddressEntity.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .user(user)
                .build();
    }
}