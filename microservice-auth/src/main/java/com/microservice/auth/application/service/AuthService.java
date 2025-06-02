package com.microservice.auth.application.service;

import com.microservice.auth.domain.model.User;

public interface AuthService {
    User register(String email, String rawPassword, String role);
    String login(String email, String rawPassword);
    boolean validateToken(String token);
    User getUserFromToken(String token);
    String generateTokenForDevice(String deviceId);
}