package com.microservice.order.web.controller;

import com.microservice.order.application.service.OrderService;
import com.microservice.order.domain.model.OrderStatusHistory;
import com.microservice.order.web.dto.OrderStatusHistoryDTO;
import com.microservice.order.web.mapper.OrderStatusHistoryDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderStatusController {

    private final OrderService service;

    public OrderStatusController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{orderId}/status-history")
    public ResponseEntity<List<OrderStatusHistoryDTO>> getStatusHistory(@PathVariable Long orderId) {
        List<OrderStatusHistory> history = service.getStatusHistory(orderId);
        List<OrderStatusHistoryDTO> dtoList = history.stream()
                .map(OrderStatusHistoryDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(dtoList);
    }
}