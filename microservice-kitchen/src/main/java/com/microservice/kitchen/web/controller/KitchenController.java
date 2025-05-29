package com.microservice.kitchen.web.controller;

import com.microservice.kitchen.application.mapper.KitchenOrderMapper;
import com.microservice.kitchen.application.service.KitchenService;
import com.microservice.kitchen.web.dto.KitchenOrderDTO;
import com.microservice.kitchen.web.dto.OrderStatusHistoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitchen")
public class KitchenController {

    private final KitchenService kitchenService;

    public KitchenController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<KitchenOrderDTO>> getPendingOrders() {
        List<KitchenOrderDTO> result = kitchenService.getPendingOrders().stream()
                .map(KitchenOrderMapper::toDto)
                .toList();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<Void> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody OrderStatusHistoryDTO dto
    ) {
        kitchenService.updateOrderStatus(id, dto.status());
        return ResponseEntity.noContent().build();
    }
}