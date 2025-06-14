package com.microservice.auth.web.dto;

public record UserCreateDTO(
        String name,
        String authEmail
) {}