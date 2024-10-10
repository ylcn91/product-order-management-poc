package com.doksanbir.productordermanagementpoc.application.strategy.order;

import com.doksanbir.productordermanagementpoc.domain.Order;
import com.doksanbir.productordermanagementpoc.domain.OrderStatus;

/**
 * Strategy interface for processing orders based on their status.
 */
public interface OrderProcessingStrategy {
    /**
     * Processes the given order.
     *
     * @param order the order to process
     */
    void process(Order order);

    /**
     * Returns the OrderStatus that this strategy handles.
     *
     * @return the handled OrderStatus
     */
    OrderStatus getHandledStatus();
}
