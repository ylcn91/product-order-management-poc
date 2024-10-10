package com.doksanbir.productordermanagementpoc.application.port.in.order;

import com.doksanbir.productordermanagementpoc.domain.Order;

/**
 * Use case interface for updating an existing order.
 */
public interface UpdateOrderUseCase {
    /**
     * Updates an existing order.
     *
     * @param order the order with updated information
     * @return the updated order
     */
    Order updateOrder(Order order);
}
