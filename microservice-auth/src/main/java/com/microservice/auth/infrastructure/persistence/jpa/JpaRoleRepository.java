package com.microservice.auth.infrastructure.persistence.jpa;

import com.microservice.auth.application.mapper.RoleMapper;
import com.microservice.auth.domain.model.Role;
import com.microservice.auth.domain.repository.RoleRepository;
import com.microservice.auth.infrastructure.persistence.springdata.SpringDataRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaRoleRepository implements RoleRepository {

    private final SpringDataRoleRepository springRepo;

    public JpaRoleRepository(SpringDataRoleRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public Optional<Role> findByName(String name) {
        return springRepo.findByName(name).map(RoleMapper::toDomain);
    }
}