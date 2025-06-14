package com.microservice.kitchen.application.service;

import com.microservice.kitchen.domain.model.OrderStatus;
import com.microservice.kitchen.web.dto.OrderDTO;

import java.util.List;

public interface KitchenService {
    List<OrderDTO> getPendingOrders();
    public void updateOrderStatus(Long orderId, OrderStatus newStatus);
}