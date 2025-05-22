// JpaAddressRepository.java
package com.microservice.user.infrastructure.persistence.jpa;

import com.microservice.user.domain.model.Address;
import com.microservice.user.domain.repository.AddressRepository;
import com.microservice.user.infrastructure.persistence.entity.AddressEntity;
import com.microservice.user.infrastructure.persistence.springdata.SpringDataAddressRepository;
import com.microservice.user.application.mapper.AddressMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaAddressRepository implements AddressRepository {

    private final SpringDataAddressRepository repository;

    public JpaAddressRepository(SpringDataAddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Address> findAll() {
        return repository.findAll().stream()
                .map(AddressMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Address> findById(Long id) {
        return repository.findById(id).map(AddressMapper::toDomain);
    }

    @Override
    public Address save(Address address) {
        AddressEntity entity = AddressMapper.toEntity(address);
        return AddressMapper.toDomain(repository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}