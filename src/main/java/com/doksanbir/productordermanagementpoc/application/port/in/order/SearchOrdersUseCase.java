package com.doksanbir.productordermanagementpoc.application.port.in.order;

import com.doksanbir.productordermanagementpoc.domain.Order;
import com.doksanbir.productordermanagementpoc.domain.OrderStatus;

import java.util.List;

/**
 * Use case interface for searching orders based on attributes.
 */
public interface SearchOrdersUseCase {
    /**
     * Searches orders based on the provided criteria.
     *
     * @param status the status of the order
     * @return the list of matching orders
     */
    List<Order> searchOrders(OrderStatus status);
}
