package com.microservice.order.web.mapper;

import com.microservice.order.domain.model.OrderStatusHistory;
import com.microservice.order.web.dto.OrderStatusHistoryDTO;

public class OrderStatusHistoryDtoMapper {

    public static OrderStatusHistoryDTO toDto(OrderStatusHistory history) {
        return new OrderStatusHistoryDTO(
                history.getStatus(),
                history.getChangedAt()
        );
    }
}