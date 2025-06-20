package com.microservice.order.infrastructure.client;

import com.microservice.order.web.dto.UserDTO;
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

    public UserDTO getAuthenticatedUser() {
        try {
            return userClient.getAuthenticatedUser();
        } catch (FeignException e) {
            throw new RuntimeException("No se pudo obtener el usuario autenticado", e);
        }
    }

    public UserDetailsDTO getUserDetailsById(Long userId, Long addressId) {
        if (userId == null || addressId == null) return null;
        try {
            return userClient.getUserDetailsForOrder(userId, addressId);
        } catch (FeignException e) {
            return null;
        }
    }

}