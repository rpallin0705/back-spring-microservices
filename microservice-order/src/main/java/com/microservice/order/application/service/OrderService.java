package com.microservice.order.application.service;

import com.microservice.order.domain.model.Order;
import com.microservice.order.web.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<Order> getAll();              // útil para procesos internos
    OrderDTO getFullOrder(Long id);    // útil para exponer al cliente
    Order create(Order order);
    void delete(Long id);
}