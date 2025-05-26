package com.microservice.order.web.dto;

public record UserDTO(
        Long id,
        String name,
        String email,
        String phone
) {}