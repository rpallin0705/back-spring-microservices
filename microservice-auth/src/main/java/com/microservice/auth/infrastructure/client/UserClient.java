package com.microservice.auth.infrastructure.client;

import com.microservice.auth.web.dto.UserCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-user", path = "/api/users")
public interface UserClient {

    @PostMapping
    ResponseEntity<Void> createUser(@RequestBody UserCreateDTO dto);
}