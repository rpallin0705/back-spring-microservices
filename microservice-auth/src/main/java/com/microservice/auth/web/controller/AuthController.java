package com.microservice.auth.web.controller;

import com.microservice.auth.application.service.AuthService;
import com.microservice.auth.domain.model.User;
import com.microservice.auth.web.dto.*;
import com.microservice.auth.web.mapper.UserDtoMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request.email(), request.password(), request.role());

        UserDTO dto = new UserDTO(
                user.id(),
                user.email(),
                user.roles().stream().map(r -> r.name()).collect(Collectors.toSet())
        );

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        String token = authService.login(request.email(), request.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/validate")
    public ResponseEntity<UserDTO> validate(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        if (!authService.validateToken(token)) {
            return ResponseEntity.badRequest().build();
        }

        User user = authService.getUserFromToken(token);
        UserDTO dto = UserDtoMapper.toDto(user);

        return ResponseEntity.ok(dto);
    }
}