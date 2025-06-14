package com.microservice.order.infrastructure.persistence.jpa;

import com.microservice.order.application.mapper.OrderStatusHistoryMapper;
import com.microservice.order.domain.model.OrderStatusHistory;
import com.microservice.order.domain.repository.OrderStatusHistoryRepository;
import com.microservice.order.infrastructure.persistence.springdata.SpringDataOrderStatusHistoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaOrderStatusHistoryRepository implements OrderStatusHistoryRepository {

    private final SpringDataOrderStatusHistoryRepository springRepo;

    public JpaOrderStatusHistoryRepository(SpringDataOrderStatusHistoryRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public List<OrderStatusHistory> findByOrderId(Long orderId) {
        return springRepo.findByOrderId(orderId).stream()
                .map(OrderStatusHistoryMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public OrderStatusHistory save(OrderStatusHistory statusHistory) {
        return OrderStatusHistoryMapper.toDomain(
                springRepo.save(OrderStatusHistoryMapper.toEntity(statusHistory))
        );
    }
}