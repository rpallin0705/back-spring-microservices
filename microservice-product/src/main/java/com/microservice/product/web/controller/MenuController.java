package com.microservice.product.web.controller;

import com.microservice.product.application.service.MenuService;
import com.microservice.product.web.dto.MenuDTO;
import com.microservice.product.web.mapper.MenuDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MenuDTO>> getAll() {
        return ResponseEntity.ok(
                service.getAll().stream()
                        .map(MenuDtoMapper::toDto)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(MenuDtoMapper.toDto(service.getById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MenuDTO> create(@RequestBody MenuDTO dto) {
        var domain = MenuDtoMapper.toDomain(dto);
        return ResponseEntity.ok(MenuDtoMapper.toDto(service.create(domain)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> update(@PathVariable Long id, @RequestBody MenuDTO dto) {
        var domain = MenuDtoMapper.toDomain(dto);
        return ResponseEntity.ok(MenuDtoMapper.toDto(service.update(id, domain)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}