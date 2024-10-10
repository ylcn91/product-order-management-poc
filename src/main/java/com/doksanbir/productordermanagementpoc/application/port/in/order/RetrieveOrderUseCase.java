package com.doksanbir.productordermanagementpoc.application.port.in.order;

import com.doksanbir.productordermanagementpoc.domain.Order;

/**
 * Use case interface for retrieving an order by ID.
 */
public interface RetrieveOrderUseCase {
    /**
     * Retrieves an order by its ID.
     *
     * @param orderId the ID of the order
     * @return the retrieved order
     */
    Order retrieveOrder(Long orderId);
}
