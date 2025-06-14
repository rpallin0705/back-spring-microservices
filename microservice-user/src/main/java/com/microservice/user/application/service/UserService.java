package com.microservice.user.application.service;

import com.microservice.user.domain.model.User;
import com.microservice.user.web.dto.UserUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();
    Optional<User> getById(Long id);
    User getCurrentUser();
    Optional<User> getByAuthEmail(String email);
    User create(User user);
    User update(Long id, User user);
    void delete(Long id);
    User updateCurrentUser(UserUpdateRequest request);
}