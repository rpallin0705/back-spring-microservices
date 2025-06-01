package com.microservice.auth.domain.model;

import java.util.Set;

public record User(
        Long id,
        String email,
        String password,
        Set<Role> roles
) {}