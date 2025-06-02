package com.microservice.auth.application.service.impl;

import com.microservice.auth.application.service.AuthService;
import com.microservice.auth.domain.model.Role;
import com.microservice.auth.domain.model.User;
import com.microservice.auth.domain.repository.RoleRepository;
import com.microservice.auth.domain.repository.UserRepository;
import com.microservice.auth.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public User register(String email, String rawPassword, String roleName) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        User user = new User(null, email, passwordEncoder.encode(rawPassword), Collections.singleton(role));
        return userRepository.save(user);
    }

    @Override
    public String login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return jwtUtil.generateToken(user);
    }

    @Override
    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }

    @Override
    public User getUserFromToken(String token) {
        String email = jwtUtil.extractUsername(token);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public String generateTokenForDevice(String deviceId) {
        return jwtUtil.generateTokenForDevice(deviceId);
    }
}