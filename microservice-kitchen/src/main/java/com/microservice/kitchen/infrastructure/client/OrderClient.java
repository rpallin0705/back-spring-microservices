package com.microservice.kitchen.infrastructure.client;

import com.microservice.kitchen.web.dto.KitchenOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "msvc-order", path = "/api/orders")
public interface OrderClient {

    @GetMapping("/kitchen")
    List<KitchenOrderDTO> getOrdersForKitchen();
}