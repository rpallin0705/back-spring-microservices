package com.microservice.auth.web.mapper;

import com.microservice.auth.domain.model.User;
import com.microservice.auth.web.dto.UserDTO;

import java.util.stream.Collectors;

public class UserDtoMapper {

    public static UserDTO toDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getRoles().stream()
                        .map(r -> r.getName())
                        .collect(Collectors.toSet())
        );
    }
}