package com.microservice.user.domain.repository;

import com.microservice.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByAuthEmail(String email);
    User save(User user);
    void deleteById(Long id);
}