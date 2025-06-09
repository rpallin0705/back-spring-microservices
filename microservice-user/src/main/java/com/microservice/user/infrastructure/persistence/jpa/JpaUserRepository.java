package com.microservice.user.infrastructure.persistence.jpa;

import com.microservice.user.domain.model.User;
import com.microservice.user.domain.repository.UserRepository;
import com.microservice.user.infrastructure.persistence.entity.UserEntity;
import com.microservice.user.infrastructure.persistence.springdata.SpringDataUserRepository;
import com.microservice.user.application.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository repository;

    public JpaUserRepository(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream()
                .map(UserMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByAuthEmail(String email) {
        return repository.findByAuthEmail(email).map(UserMapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        return UserMapper.toDomain(repository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}