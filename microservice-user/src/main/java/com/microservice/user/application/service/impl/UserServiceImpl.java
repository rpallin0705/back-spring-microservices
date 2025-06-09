package com.microservice.user.application.service.impl;

import com.microservice.user.application.service.UserService;
import com.microservice.user.domain.model.Address;
import com.microservice.user.domain.model.User;
import com.microservice.user.domain.repository.UserRepository;
import com.microservice.user.web.dto.UserUpdateRequest;
import com.microservice.user.web.mapper.AddressDtoMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getByAuthEmail(String email) {
        return userRepository.findByAuthEmail(email);
    }

    @Override
    public User getCurrentUser() {
        return getAuthenticatedUserByEmail();
    }

    @Override
    public User create(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existing.setName(user.getName());
        existing.setPhone(user.getPhone());
        existing.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateCurrentUser(UserUpdateRequest request) {
        User user = getAuthenticatedUserByEmail();
        boolean updated = false;

        if (request.phone() != null) {
            user.setPhone(request.phone());
            updated = true;
        }

        if (request.address() != null) {
            Address newAddress = AddressDtoMapper.toDomain(request.address());
            user.getAddresses().add(newAddress);
            updated = true;
        }

        if (updated) {
            user.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(user);
        }

        return user;
    }

    private User getAuthenticatedUserByEmail() {
        String email = getAuthenticatedUserEmail();
        return userRepository.findByAuthEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }

    private String getAuthenticatedUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String email) {
            return email;
        }
        throw new RuntimeException("No se pudo obtener el email del usuario autenticado");
    }
}