package com.microservice.order.application.service;

import com.microservice.order.domain.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();
    Order getById(Long id);
    Order create(Order order);
    void delete(Long id);
}