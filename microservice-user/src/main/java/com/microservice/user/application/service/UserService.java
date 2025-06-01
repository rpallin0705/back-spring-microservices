package com.microservice.user.application.service;

import com.microservice.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();
    Optional<User> getById(Long id);
    Optional<User> getByAuthEmail(String email);
    User create(User user);
    User update(Long id, User user);
    void delete(Long id);
}