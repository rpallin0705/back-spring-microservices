package com.microservice.auth.infrastructure.persistence.jpa;

import com.microservice.auth.application.mapper.UserMapper;
import com.microservice.auth.domain.model.User;
import com.microservice.auth.domain.repository.UserRepository;
import com.microservice.auth.infrastructure.persistence.springdata.SpringDataUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository springRepo;

    public JpaUserRepository(SpringDataUserRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springRepo.findByEmail(email).map(UserMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return springRepo.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return UserMapper.toDomain(
                springRepo.save(UserMapper.toEntity(user))
        );
    }
}