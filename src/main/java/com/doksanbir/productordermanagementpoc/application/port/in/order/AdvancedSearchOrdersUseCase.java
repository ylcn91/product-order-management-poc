package com.doksanbir.productordermanagementpoc.application.port.in.order;

import com.doksanbir.productordermanagementpoc.domain.Order;
import com.doksanbir.productordermanagementpoc.domain.OrderStatus;

import java.util.List;

/**
 * Use case interface for advanced searching of orders based on multiple attributes.
 */
public interface AdvancedSearchOrdersUseCase {
    /**
     * Performs an advanced search of orders based on status and product ID.
     *
     * @param status    the status of the orders to search for (optional)
     * @param productId the ID of the product associated with the orders (optional)
     * @return the list of matching orders
     */
    List<Order> advancedSearchOrders(OrderStatus status, Long productId);
}
