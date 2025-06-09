package com.microservice.user.web.mapper;

import com.microservice.user.domain.model.User;
import com.microservice.user.web.dto.AddressDTO;
import com.microservice.user.web.dto.UserDetailsDTO;

public class UserDetailsDtoMapper {

    public static UserDetailsDTO toDto(User user, Long addressId) {
        AddressDTO address = user.getAddresses() != null
                ? user.getAddresses().stream()
                .filter(addr -> addr.getId().equals(addressId))
                .findFirst()
                .map(AddressDtoMapper::toDto)
                .orElse(null)
                : null;

        return new UserDetailsDTO(
                user.getId(),
                user.getName(),
                user.getAuthEmail(),
                user.getPhone(),
                address
        );
    }
}