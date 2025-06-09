package com.microservice.user.web.dto;

public record UserCreateDTO(
        String name,
        String authEmail
) {}