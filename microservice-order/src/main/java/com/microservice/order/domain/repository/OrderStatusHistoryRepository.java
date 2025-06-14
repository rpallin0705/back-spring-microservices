package com.microservice.order.domain.repository;

import com.microservice.order.domain.model.OrderStatusHistory;

import java.util.List;

public interface OrderStatusHistoryRepository {
    List<OrderStatusHistory> findByOrderId(Long orderId);
    OrderStatusHistory save(OrderStatusHistory statusHistory);
}