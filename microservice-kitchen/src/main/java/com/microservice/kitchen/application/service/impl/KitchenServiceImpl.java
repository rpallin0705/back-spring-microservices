package com.microservice.kitchen.application.service.impl;

import com.microservice.kitchen.application.mapper.KitchenOrderMapper;
import com.microservice.kitchen.application.service.KitchenService;
import com.microservice.kitchen.domain.model.KitchenOrder;
import com.microservice.kitchen.domain.model.OrderStatus;
import com.microservice.kitchen.infrastructure.client.OrderClient;
import com.microservice.kitchen.web.dto.OrderStatusHistoryDTO;
import com.microservice.kitchen.websocket.KitchenWebSocketNotifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KitchenServiceImpl implements KitchenService {

    private final OrderClient orderClient;
    private final KitchenWebSocketNotifier notifier;

    public KitchenServiceImpl(OrderClient orderClient, KitchenWebSocketNotifier notifier) {
        this.orderClient = orderClient;
        this.notifier = notifier;
    }

    @Override
    public List<KitchenOrder> getPendingOrders() {
        return orderClient.getOrdersForKitchen().stream()
                .map(KitchenOrderMapper::toDomain)
                .toList();
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
        OrderStatusHistoryDTO dto = new OrderStatusHistoryDTO(
                newStatus,
                LocalDateTime.now()
        );

        orderClient.updateOrderStatus(orderId, dto);

        KitchenOrder updatedOrder = KitchenOrderMapper.fromStatusUpdate(orderId, newStatus);
        notifier.notifyOrderUpdate(updatedOrder);
    }
}