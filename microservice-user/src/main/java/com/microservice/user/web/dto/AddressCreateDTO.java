package com.microservice.user.web.dto;

public record AddressCreateDTO(
        String street,
        String city,
        String postalCode,
        String country,
        Long userId
) {}