package com.microservice.order.infrastructure.client;

import com.microservice.order.web.dto.MenuDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-product", path = "/api/menus", contextId = "menuClient")
public interface MenuClient {
    @GetMapping("/{id}")
    MenuDTO getMenuById(@PathVariable Long id);
}