package com.microservice.order.web.controller;

import com.microservice.order.application.service.OrderService;
import com.microservice.order.domain.model.OrderStatusHistory;
import com.microservice.order.web.dto.KitchenOrderDTO;
import com.microservice.order.web.dto.OrderStatusHistoryDTO;
import com.microservice.order.web.mapper.OrderStatusHistoryDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders/kitchen")
public class KitchenOrderController {

    private final OrderService service;

    public KitchenOrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<KitchenOrderDTO>> getKitchenOrders() {
        return ResponseEntity.ok(service.getOrdersForKitchen());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateKitchenOrderStatus(
            @PathVariable Long id,
            @RequestBody OrderStatusHistoryDTO dto
    ) {
        OrderStatusHistory history = OrderStatusHistoryDtoMapper.toDomain(dto, id);
        service.updateStatus(id, history.getStatus());
        return ResponseEntity.noContent().build();
    }
}