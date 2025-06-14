package com.microservice.user.web.mapper;

import com.microservice.user.domain.model.Address;
import com.microservice.user.web.dto.AddressCreateDTO;
import com.microservice.user.web.dto.AddressDTO;

public class AddressDtoMapper {

    public static AddressDTO toDto(Address address) {
        if (address == null) return null;

        return new AddressDTO(
                address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getPostalCode(),
                address.getCountry(),
                address.getUserId()
        );
    }

    public static Address toDomain(AddressCreateDTO dto) {
        if (dto == null) return null;

        return Address.builder()
                .street(dto.street())
                .city(dto.city())
                .postalCode(dto.postalCode())
                .country(dto.country())
                .userId(dto.userId())
                .build();
    }
}