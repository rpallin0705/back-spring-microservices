package com.microservice.user.web.mapper;

import com.microservice.user.domain.model.User;
import com.microservice.user.web.dto.UserCreateDTO;
import com.microservice.user.web.dto.UserDTO;

import java.util.List;

public class UserDtoMapper {

    public static UserDTO toDto(User user) {
        if (user == null) return null;

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getAuthEmail(),
                user.getPhone(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getAddresses() != null
                        ? user.getAddresses().stream().map(AddressDtoMapper::toDto).toList()
                        : List.of()
        );
    }

    public static User toDomain(UserCreateDTO dto) {
        if (dto == null) return null;

        return User.builder()
                .name(dto.name())
                .authEmail(dto.authEmail())
                .build();
    }
}