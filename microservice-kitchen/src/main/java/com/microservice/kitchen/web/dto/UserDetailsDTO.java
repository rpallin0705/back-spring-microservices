package com.microservice.order.web.dto;

import com.microservice.kitchen.web.dto.AddressDTO;

public record UserDetailsDTO(
        Long id,
        String name,
        String email,
        String phone,
        AddressDTO address
) {}