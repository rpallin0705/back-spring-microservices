package com.microservice.order.web.controller;

import com.microservice.order.application.service.OrderService;
import com.microservice.order.domain.model.OrderStatus;
import com.microservice.order.domain.model.OrderStatusHistory;
import com.microservice.order.web.dto.KitchenOrderDTO;
import com.microservice.order.web.dto.OrderDTO;
import com.microservice.order.web.dto.OrderStatusHistoryDTO;
import com.microservice.order.web.dto.UpdateOrderStatusDTO;
import com.microservice.order.web.mapper.OrderStatusHistoryDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders/kitchen")
public class KitchenOrderController {

    private final OrderService service;

    public KitchenOrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('COOK')")
    public ResponseEntity<List<OrderDTO>> getKitchenOrders() {
        return ResponseEntity.ok(service.getAllFullOrders());
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('COOK')")
    public ResponseEntity<Void> updateKitchenOrderStatus(
            @PathVariable Long id,
            @RequestBody UpdateOrderStatusDTO dto
    ) {

        service.updateStatus(id, OrderStatus.valueOf(dto.status()));
        return ResponseEntity.noContent().build();
    }
}
