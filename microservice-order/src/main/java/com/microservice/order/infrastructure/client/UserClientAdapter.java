package com.microservice.order.infrastructure.client;

import com.microservice.order.web.dto.UserDetailsDTO;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class UserClientAdapter {
    private final UserClient userClient;
    private final HttpServletRequest request;

    public UserClientAdapter(UserClient userClient, HttpServletRequest request) {
        this.userClient = userClient;
        this.request = request;
    }

    public UserDetailsDTO getUserDetailsIfPresent(Long addressId) {
        if (addressId == null) return null;
        try {
            return userClient.getAuthenticatedUserDetailsForOrder(addressId);
        } catch (FeignException.Forbidden e) {
            return null;
        }
    }
}