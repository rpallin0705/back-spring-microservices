package com.microservice.order.web.controller;

import com.microservice.order.application.service.OrderService;
import com.microservice.order.domain.model.Order;
import com.microservice.order.domain.repository.OrderStatusHistoryRepository;
import com.microservice.order.web.dto.OrderCreateDTO;
import com.microservice.order.web.dto.OrderDTO;
import com.microservice.order.web.dto.OrderStatusHistoryDTO;
import com.microservice.order.web.mapper.OrderDtoMapper;
import com.microservice.order.web.mapper.OrderStatusHistoryDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;
    private final OrderStatusHistoryRepository statusHistoryRepository;

    public OrderController(OrderService service, OrderStatusHistoryRepository statusHistoryRepository) {
        this.service = service;
        this.statusHistoryRepository = statusHistoryRepository;
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
    public ResponseEntity<OrderDTO> create(@RequestBody OrderCreateDTO dto) {
        Order created = service.create(OrderDtoMapper.toDomain(dto));
        return ResponseEntity.ok(service.getFullOrder(created.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<OrderStatusHistoryDTO>> getHistory(@PathVariable Long id) {
        return ResponseEntity.ok(
                statusHistoryRepository.findByOrderId(id).stream()
                        .map(OrderStatusHistoryDtoMapper::toDto)
                        .collect(Collectors.toList())
        );
    }
}