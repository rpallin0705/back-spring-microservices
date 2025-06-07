package com.microservice.user.web.controller;

import com.microservice.user.application.service.UserService;
import com.microservice.user.domain.model.User;
import com.microservice.user.web.dto.UserCreateDTO;
import com.microservice.user.web.dto.UserDTO;
import com.microservice.user.web.dto.UserDetailsDTO;
import com.microservice.user.web.dto.UserUpdateRequest;
import com.microservice.user.web.mapper.UserDetailsDtoMapper;
import com.microservice.user.web.mapper.UserDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> result = userService.getAll().stream()
                .map(UserDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        return userService.getById(id)
                .map(user -> ResponseEntity.ok(UserDtoMapper.toDto(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDTO> getCurrentUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(UserDtoMapper.toDto(user));
    }

    @GetMapping("/me/details/{addressId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDetailsDTO> getAuthenticatedUserDetailsForOrder(@PathVariable Long addressId) {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(UserDetailsDtoMapper.toDto(user, addressId));
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserDTO> create(@RequestBody UserCreateDTO dto) {
        User created = userService.create(UserDtoMapper.toDomain(dto));
        return ResponseEntity.ok(UserDtoMapper.toDto(created));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDTO> updateMyUser(@RequestBody UserUpdateRequest request) {
        User updated = userService.updateCurrentUser(request);
        return ResponseEntity.ok(UserDtoMapper.toDto(updated));
    }
}