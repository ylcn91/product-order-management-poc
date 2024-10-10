package com.doksanbir.productordermanagementpoc.application.port.out.order;

import com.doksanbir.productordermanagementpoc.domain.Order;
import com.doksanbir.productordermanagementpoc.domain.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * Repository port interface for order persistence operations.
 */
public interface OrderRepositoryPort {
    /**
     * Saves an order.
     *
     * @param order the order to save
     * @return the saved order
     */
    Order save(Order order);

    /**
     * Finds an order by its ID.
     *
     * @param orderId the ID of the order
     * @return an optional containing the order if found
     */
    Optional<Order> findById(Long orderId);

    /**
     * Deletes an order by its ID.
     *
     * @param orderId the ID of the order to delete
     */
    void deleteById(Long orderId);

    /**
     * Retrieves all orders.
     *
     * @return the list of orders
     */
    List<Order> findAll();

    /**
     * Searches orders based on status.
     *
     * @param status the status of the order
     * @return the list of matching orders
     */
    List<Order> search(OrderStatus status);

    /**
     * Finds orders based on specifications.
     *
     * @param specification the specification to filter orders
     * @return the list of matching orders
     */
    List<Order> findAll(Specification<Order> specification);
}
