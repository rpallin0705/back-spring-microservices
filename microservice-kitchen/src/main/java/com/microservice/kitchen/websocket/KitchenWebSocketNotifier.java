package com.microservice.kitchen.websocket;

import com.microservice.kitchen.domain.model.KitchenOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class KitchenWebSocketNotifier {

    private final SimpMessagingTemplate messagingTemplate;

    public void notifyOrderUpdate(KitchenOrder order) {
        Map<String, Object> payload = Map.of(
                "id", order.getId(),
                "status", order.getStatus().name()
        );
        messagingTemplate.convertAndSend("/topic/order-status", payload);
    }
}