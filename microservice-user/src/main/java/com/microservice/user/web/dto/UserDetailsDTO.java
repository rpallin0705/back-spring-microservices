package com.microservice.user.web.dto;

public record UserDetailsDTO(
        Long id,
        String name,
        String authEmail,
        String phone,
        AddressDTO address
) {}