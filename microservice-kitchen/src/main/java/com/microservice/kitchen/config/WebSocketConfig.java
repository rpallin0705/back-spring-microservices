package com.microservice.kitchen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Prefijo de suscripción
        config.enableSimpleBroker("/topic");
        // prefijo para mensajes entrantes al servidor
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // punto de conexión desde frontend
        registry.addEndpoint("/ws-kitchen").setAllowedOriginPatterns("*").withSockJS();
    }
}