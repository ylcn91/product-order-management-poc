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
 * <p>
 * This class implements the {@link OrderRepositoryPort} interface and provides
 * an adapter for interacting with the underlying database using Spring Data JPA.
 */
@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository orderJpaRepository;

    /**
     * Saves an order to the database.
     *
     * @param order the order to save
     * @return the saved order
     */
    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }

    /**
     * Finds an order by its ID.
     *
     * @param orderId the ID of the order
     * @return an optional containing the order if found
     */
    @Override
    public Optional<Order> findById(Long orderId) {
        return orderJpaRepository.findById(orderId);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param orderId the ID of the order to delete
     */
    @Override
    public void deleteById(Long orderId) {
        orderJpaRepository.deleteById(orderId);
    }

    /**
     * Retrieves all orders from the database.
     *
     * @return a list of all orders
     */
    @Override
    public List<Order> findAll() {
        return orderJpaRepository.findAll();
    }

    /**
     * Searches orders by their status.
     *
     * @param status the status to search for
     * @return a list of orders matching the given status
     */
    @Override
    public List<Order> search(OrderStatus status) {
        return orderJpaRepository.findByStatus(status);
    }

    /**
     * Finds orders based on specifications.
     *
     * @param specification the specification to filter orders
     * @return a list of orders matching the given specification
     */
    @Override
    public List<Order> findAll(Specification<Order> specification) {
        return orderJpaRepository.findAll(specification);
    }
}