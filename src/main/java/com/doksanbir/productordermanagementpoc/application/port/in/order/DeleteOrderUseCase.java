package com.doksanbir.productordermanagementpoc.application.port.in.order;

/**
 * Use case interface for deleting an order.
 */
public interface DeleteOrderUseCase {
    /**
     * Deletes an order by its ID.
     *
     * @param orderId the ID of the order to delete
     */
    void deleteOrder(Long orderId);
}
