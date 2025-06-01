package com.microservice.user.web.controller;

import com.microservice.user.application.service.UserService;
import com.microservice.user.domain.model.User;
import com.microservice.user.web.dto.UserCreateDTO;
import com.microservice.user.web.dto.UserDTO;
import com.microservice.user.web.dto.UserDetailsDTO;
import com.microservice.user.web.mapper.UserDetailsDtoMapper;
import com.microservice.user.web.mapper.UserDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> result = userService.getAll().stream()
                .map(UserDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        return userService.getById(id)
                .map(user -> ResponseEntity.ok(UserDtoMapper.toDto(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/details/{addressId}")
    public ResponseEntity<UserDetailsDTO> getUserDetailsForOrder(
            @PathVariable Long userId,
            @PathVariable Long addressId) {

        return userService.getById(userId)
                .map(user -> ResponseEntity.ok(UserDetailsDtoMapper.toDto(user, addressId)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserCreateDTO dto) {
        User created = userService.create(UserDtoMapper.toDomain(dto));
        return ResponseEntity.ok(UserDtoMapper.toDto(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}