package com.microservice.order.application.service.impl;

import com.microservice.order.application.service.OrderService;
import com.microservice.order.domain.model.Order;
import com.microservice.order.domain.model.OrderStatusHistory;
import com.microservice.order.domain.repository.OrderRepository;
import com.microservice.order.domain.repository.OrderStatusHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusHistoryRepository statusHistoryRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderStatusHistoryRepository statusHistoryRepository) {
        this.orderRepository = orderRepository;
        this.statusHistoryRepository = statusHistoryRepository;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
    }

    @Override
    public Order create(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        Order saved = orderRepository.save(order);

        statusHistoryRepository.save(
                OrderStatusHistory.builder()
                        .orderId(saved.getId())
                        .status(saved.getStatus())
                        .changedAt(LocalDateTime.now())
                        .build()
        );

        return saved;
    }

    @Override
    public void delete(Long id) {
        getById(id);
        orderRepository.deleteById(id);
    }
}