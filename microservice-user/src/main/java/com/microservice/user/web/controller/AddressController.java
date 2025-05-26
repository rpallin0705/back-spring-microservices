package com.microservice.user.web.controller;

import com.microservice.user.application.service.AddressService;
import com.microservice.user.domain.model.Address;
import com.microservice.user.web.dto.AddressCreateDTO;
import com.microservice.user.web.dto.AddressDTO;
import com.microservice.user.web.mapper.AddressDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAll() {
        List<AddressDTO> result = addressService.getAll().stream()
                .map(AddressDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getById(@PathVariable Long id) {
        return addressService.getById(id)
                .map(address -> ResponseEntity.ok(AddressDtoMapper.toDto(address)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AddressDTO> create(@RequestBody AddressCreateDTO dto) {
        Address created = addressService.create(AddressDtoMapper.toDomain(dto));
        return ResponseEntity.ok(AddressDtoMapper.toDto(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}