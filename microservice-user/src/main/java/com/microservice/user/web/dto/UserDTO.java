package com.microservice.user.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record UserDTO(
        Long id,
        String name,
        String authEmail,
        String phone,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<AddressDTO> addresses
) {}