package com.microservice.order.domain.model;

public enum OrderStatus {
    CREATED,
    PREPARING,
    READY,
    DISPATCHED,
    DELIVERED,
    CANCELLED;

    public boolean canTransitionTo(OrderStatus next) {
        return switch (this) {
            case CREATED -> next == PREPARING;
            case PREPARING -> next == READY;
            case READY -> next == DISPATCHED;
            case DISPATCHED -> next == DELIVERED;
            case DELIVERED, CANCELLED -> false;
        };
    }
}