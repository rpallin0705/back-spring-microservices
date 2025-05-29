package com.microservice.kitchen.websocket;

import com.microservice.kitchen.domain.model.KitchenOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KitchenWebSocketNotifier {

    private final SimpMessagingTemplate messagingTemplate;

    public void notifyOrderUpdate(KitchenOrder order) {
        messagingTemplate.convertAndSend("/topic/order-status", order);
    }
}