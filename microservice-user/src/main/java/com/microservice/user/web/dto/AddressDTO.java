package com.microservice.user.web.dto;

public record AddressDTO(
        Long id,
        String street,
        String city,
        String postalCode,
        String country,
        Long userId
) {}