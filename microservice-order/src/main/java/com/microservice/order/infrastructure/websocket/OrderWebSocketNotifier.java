package com.microservice.order.infrastructure.websocket;

import com.microservice.order.web.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderWebSocketNotifier {

    private final SimpMessagingTemplate messagingTemplate;

    public void notifyNewOrder(Long orderId) {
        messagingTemplate.convertAndSend("/topic/orders/new", orderId);
    }
}