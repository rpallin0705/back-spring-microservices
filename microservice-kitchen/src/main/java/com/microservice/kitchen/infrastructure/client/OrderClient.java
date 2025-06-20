package com.microservice.kitchen.infrastructure.client;

import com.microservice.kitchen.infrastructure.config.FeignClientConfig;
import com.microservice.kitchen.web.dto.KitchenOrderDTO;
import com.microservice.kitchen.web.dto.OrderDTO;
import com.microservice.kitchen.web.dto.OrderStatusHistoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "msvc-order",
        path = "/api/orders/kitchen",
        configuration = FeignClientConfig.class
)
public interface OrderClient {
    @GetMapping
    List<OrderDTO> getOrdersForKitchen();

    @PutMapping("/{id}/status")
    void updateOrderStatus(@PathVariable("id") Long orderId, @RequestBody OrderStatusHistoryDTO dto);
}