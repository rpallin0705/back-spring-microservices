package com.microservice.auth.domain.repository;

import com.microservice.auth.domain.model.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(String name);
}