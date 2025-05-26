package com.microservice.user.domain.repository;

import com.microservice.user.domain.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    List<Address> findAll();
    Optional<Address> findById(Long id);
    Address save(Address address);
    void deleteById(Long id);
}