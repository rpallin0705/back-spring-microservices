package com.microservice.user.web.dto;

public record UserDetailsDTO(
        Long id,
        String name,
        String email,
        String phone,
        AddressDTO address
) {}