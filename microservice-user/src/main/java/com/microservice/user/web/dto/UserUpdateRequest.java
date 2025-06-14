package com.microservice.user.web.dto;

public record UserUpdateRequest(
        String phone,
        AddressCreateDTO address
) {}