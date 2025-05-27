package com.microservice.kitchen.application.service.impl;

import com.microservice.kitchen.application.mapper.KitchenOrderMapper;
import com.microservice.kitchen.domain.model.KitchenOrder;
import com.microservice.kitchen.infrastructure.client.OrderClient;
import com.microservice.kitchen.web.dto.KitchenOrderDTO;
import org.springframework.stereotype.Service;
import com.microservice.kitchen.application.service.KitchenService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KitchenServiceImpl implements KitchenService {

    private final OrderClient orderClient;

    public KitchenServiceImpl(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @Override
    public List<KitchenOrder> getPendingOrders() {
        return orderClient.getOrdersForKitchen().stream()
                .map(KitchenOrderMapper::toDomain)
                .toList();
    }
}