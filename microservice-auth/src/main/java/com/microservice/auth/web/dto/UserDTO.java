package com.microservice.auth.web.dto;

import java.util.Set;

public record UserDTO(
        Long id,
        String email,
        Set<String> roles
) {}