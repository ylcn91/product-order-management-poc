package com.doksanbir.productordermanagementpoc.application.port.in.order;

import com.doksanbir.productordermanagementpoc.domain.Order;

import java.util.List;

/**
 * Use case interface for listing all orders with optional filtering and pagination.
 */
public interface ListOrdersUseCase {
    /**
     * Lists all orders with optional filtering.
     *
     * @return the list of orders
     */
    List<Order> listOrders();
}
