package com.microservice.order.infrastructure.client;

import com.microservice.order.web.dto.UserDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-user", path = "/api/users")
public interface UserClient {

    @GetMapping("/{userId}/details/{addressId}")
    UserDetailsDTO getUserDetailsForOrder(@PathVariable Long userId, @PathVariable Long addressId);
}