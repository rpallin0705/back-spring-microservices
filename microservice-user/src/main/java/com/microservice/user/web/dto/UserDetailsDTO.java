package com.microservice.user.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record UserDetailsDTO(
        Long id,
        String name,
        String email,
        String phone,
        AddressDTO address
) {}