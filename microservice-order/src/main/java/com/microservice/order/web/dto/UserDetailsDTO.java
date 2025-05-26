package com.microservice.order.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record UserDetailsDTO(
        Long id,
        String name,
        String email,
        String phone,
        com.microservice.user.web.dto.AddressDTO address
) {}