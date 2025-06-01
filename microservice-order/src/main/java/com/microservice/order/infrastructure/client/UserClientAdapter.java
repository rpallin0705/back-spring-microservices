package com.microservice.order.infrastructure.client;

import com.microservice.order.web.dto.UserDetailsDTO;
import org.springframework.stereotype.Component;

@Component
public class UserClientAdapter {

    private final UserClient userClient;

    public UserClientAdapter(UserClient userClient) {
        this.userClient = userClient;
    }

    public UserDetailsDTO getUserDetailsIfPresent(Long userId, Long addressId) {
        if (userId == null || addressId == null) return null;
        return userClient.getUserDetailsForOrder(userId, addressId);
    }
}