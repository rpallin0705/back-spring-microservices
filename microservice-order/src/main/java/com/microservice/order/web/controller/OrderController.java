package com.microservice.order.web.controller;

import com.microservice.order.application.service.OrderService;
import com.microservice.order.domain.model.Order;
import com.microservice.order.web.dto.*;
import com.microservice.order.web.mapper.OrderDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAll() {
        return ResponseEntity.ok(service.getAllFullOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFullOrder(id));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<OrderDTO> create(@RequestBody OrderCreateDTO dto) {
        Order created = service.create(OrderDtoMapper.toDomain(dto));
        return ResponseEntity.ok(service.getFullOrder(created.getId()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
