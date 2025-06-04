package com.microservice.auth.web.controller;

import com.microservice.auth.application.service.AuthService;
import com.microservice.auth.application.service.DeviceService;
import com.microservice.auth.domain.model.Device;
import com.microservice.auth.domain.model.User;
import com.microservice.auth.web.dto.*;
import com.microservice.auth.web.mapper.UserDtoMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final DeviceService deviceService;

    public AuthController(AuthService authService, DeviceService deviceService) {
        this.authService = authService;
        this.deviceService = deviceService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request.email(), request.password());
        UserDTO dto = new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toSet())
        );
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        String token = authService.login(request.email(), request.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login/device")
    public ResponseEntity<AuthResponse> loginDevice(@RequestBody DeviceLoginRequest request) {
        return deviceService.findByDeviceId(request.deviceId())
                .filter(device -> device.getSecret().equals(request.secret()))
                .map(device -> {
                    String token = authService.generateTokenForDevice(device.getDeviceId());
                    return ResponseEntity.ok(new AuthResponse(token));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        if (!authService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
        }
        User user = authService.getUserFromToken(token);
        return ResponseEntity.ok(UserDtoMapper.toDto(user));
    }
}