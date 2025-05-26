package com.microservice.order.domain.model;

public enum OrderStatus {
    CREATED,
    PREPARING,
    READY,
    DISPATCHED,
    DELIVERED,
    CANCELLED
}