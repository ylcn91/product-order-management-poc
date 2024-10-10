package com.doksanbir.productordermanagementpoc.infrastructure.adapter.out.order.persistence;

import com.doksanbir.productordermanagementpoc.domain.Order;
import com.doksanbir.productordermanagementpoc.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the {@link Order} entity.
 * <p>
 * Provides CRUD operations and custom queries for the order entity.
 * <p>
 * Extends {@link JpaSpecificationExecutor} to enable the execution of dynamic queries using the Specification pattern.
 *
 * <p>{@link JpaSpecificationExecutor} allows dynamic query construction at runtime. By using specifications, complex
 * queries can be built in a type-safe manner and combined using logical operators (e.g., AND, OR). This enables
 * separation of query logic from repository methods and supports reusable query conditions that can be combined
 * as needed. The Specification pattern is particularly useful when query parameters are not known upfront and
 * need to be constructed dynamically.
 */
@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    /**
     * Finds orders by their status.
     *
     * @param status the status to search for
     * @return a list of orders matching the given status
     */
    List<Order> findByStatus(OrderStatus status);
}