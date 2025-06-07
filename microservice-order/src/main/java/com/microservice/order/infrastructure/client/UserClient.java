package com.microservice.order.infrastructure.client;

import com.microservice.order.infrastructure.config.FeignClientConfig;
import com.microservice.order.web.dto.UserDTO;
import com.microservice.order.web.dto.UserDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-user", path = "/api/users", configuration = FeignClientConfig.class)
public interface UserClient {

    @GetMapping("/me")
    UserDTO getAuthenticatedUser();

    @GetMapping("/me/details/{addressId}")
    UserDetailsDTO getAuthenticatedUserDetailsForOrder(
            @PathVariable("addressId") Long addressId
    );
}