package com.microservice.user.web.dto;

import com.microservice.user.domain.model.UserRole;

public record UserCreateDTO(
        String name,
        String email,
        String phone,
        UserRole role
) {}