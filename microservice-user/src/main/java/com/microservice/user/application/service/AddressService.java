package com.microservice.user.application.service;

import com.microservice.user.domain.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<Address> getAll();
    Optional<Address> getById(Long id);
    Address create(Address address);
    Address update(Long id, Address address);
    void delete(Long id);
}