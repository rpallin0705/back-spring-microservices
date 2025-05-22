package com.microservice.user.application.service.impl;

import com.microservice.user.application.mapper.AddressMapper;
import com.microservice.user.application.service.AddressService;
import com.microservice.user.domain.model.Address;
import com.microservice.user.domain.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> getById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Long id, Address address) {
        Address existing = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));

        existing.setStreet(address.getStreet());
        existing.setCity(address.getCity());
        existing.setPostalCode(address.getPostalCode());
        existing.setCountry(address.getCountry());

        return addressRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}