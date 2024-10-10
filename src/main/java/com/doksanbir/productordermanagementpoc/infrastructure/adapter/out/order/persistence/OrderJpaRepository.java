package com.doksanbir.productordermanagementpoc.infrastructure.adapter.out.order.persistence;

import com.doksanbir.productordermanagementpoc.domain.Order;
import com.doksanbir.productordermanagementpoc.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for Order entity.
 */
@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    /**
     * Finds orders by status.
     *
     * @param status the status to search for
     * @return the list of matching orders
     */
    List<Order> findByStatus(OrderStatus status);
}
