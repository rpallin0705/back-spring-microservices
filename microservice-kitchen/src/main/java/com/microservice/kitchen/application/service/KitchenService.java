package com.microservice.kitchen.application.service;

import com.microservice.kitchen.domain.model.KitchenOrder;
import com.microservice.kitchen.domain.model.OrderStatus;
import com.microservice.kitchen.web.dto.KitchenOrderDTO;
import java.util.List;

public interface KitchenService {
    List<KitchenOrder> getPendingOrders();
    public void updateOrderStatus(Long orderId, OrderStatus newStatus);
}