package com.microservice.user.web.dto;

import com.microservice.user.domain.model.UserRole;

import java.time.LocalDateTime;
import java.util.List;

public record UserDTO(
        Long id,
        String name,
        String email,
        String phone,
        UserRole role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<AddressDTO> addresses
) {}