package com.doksanbir.productordermanagementpoc.infrastructure.adapter.out.order;

import com.doksanbir.productordermanagementpoc.application.port.out.order.OrderRepositoryPort;
import com.doksanbir.productordermanagementpoc.domain.Order;
import com.doksanbir.productordermanagementpoc.domain.OrderStatus;
import com.doksanbir.productordermanagementpoc.infrastructure.adapter.out.order.persistence.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Adapter implementation for order repository using Spring Data JPA.
 */
@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return orderJpaRepository.findById(orderId);
    }

    @Override
    public void deleteById(Long orderId) {
        orderJpaRepository.deleteById(orderId);
    }

    @Override
    public List<Order> findAll() {
        return orderJpaRepository.findAll();
    }

    @Override
    public List<Order> search(OrderStatus status) {
        return orderJpaRepository.findByStatus(status);
    }

    @Override
    public List<Order> findAll(Specification<Order> specification) {
        return orderJpaRepository.findAll(specification);
    }
}
