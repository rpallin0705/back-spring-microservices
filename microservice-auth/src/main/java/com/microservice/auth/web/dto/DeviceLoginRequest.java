package com.microservice.auth.web.dto;

public record DeviceLoginRequest(
        String deviceId,
        String secret
) {}