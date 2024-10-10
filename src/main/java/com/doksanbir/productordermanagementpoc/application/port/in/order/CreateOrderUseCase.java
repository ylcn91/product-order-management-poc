package com.doksanbir.productordermanagementpoc.application.port.in.order;

import com.doksanbir.productordermanagementpoc.domain.Order;

/**
 * Use case interface for creating a new order.
 */
public interface CreateOrderUseCase {
    /**
     * Creates a new order.
     *
     * @param order the order to create
     * @return the created order
     */
    Order createOrder(Order order);
}
