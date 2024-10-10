package com.doksanbir.productordermanagementpoc.infrastructure.adapter.in.order;

import com.doksanbir.productordermanagementpoc.application.port.in.order.*;
import com.doksanbir.productordermanagementpoc.domain.Order;
import com.doksanbir.productordermanagementpoc.domain.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing orders.
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final RetrieveOrderUseCase retrieveOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;
    private final ListOrdersUseCase listOrdersUseCase;
    private final SearchOrdersUseCase searchOrdersUseCase;
    private final AdvancedSearchOrdersUseCase advancedSearchOrdersUseCase;

    /**
     * Creates a new order.
     *
     * @param order the order to create
     * @return the created order
     */
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        log.info("Creating order for product ID: {}", order.getProduct().getId());
        return createOrderUseCase.createOrder(order);
    }

    /**
     * Retrieves an order by ID.
     *
     * @param orderId the ID of the order
     * @return the retrieved order
     */
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable("id") Long orderId) {
        log.info("Retrieving order with ID: {}", orderId);
        return retrieveOrderUseCase.retrieveOrder(orderId);
    }

    /**
     * Updates an existing order.
     *
     * @param order the order with updated information
     * @return the updated order
     */
    @PutMapping
    public Order updateOrder(@RequestBody Order order) {
        log.info("Updating order with ID: {}", order.getId());
        return updateOrderUseCase.updateOrder(order);
    }

    /**
     * Deletes an order by ID.
     *
     * @param orderId the ID of the order to delete
     */
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Long orderId) {
        log.info("Deleting order with ID: {}", orderId);
        deleteOrderUseCase.deleteOrder(orderId);
    }

    /**
     * Lists all orders.
     *
     * @return the list of orders
     */
    @GetMapping
    public List<Order> listOrders() {
        log.info("Listing all orders");
        return listOrdersUseCase.listOrders();
    }

    /**
     * Searches for orders based on status.
     *
     * @param status the status of the order
     * @return the list of matching orders
     */
    @GetMapping("/search")
    public List<Order> searchOrders(@RequestParam(required = false) OrderStatus status) {
        log.info("Searching orders with status: {}", status);
        return searchOrdersUseCase.searchOrders(status);
    }

    /**
     * Performs an advanced search of orders based on status and product ID.
     *
     * @param status    the status of the order (optional)
     * @param productId the ID of the product associated with the order (optional)
     * @return the list of matching orders
     */
    @GetMapping("/advanced-search")
    public List<Order> advancedSearchOrders(
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(required = false) Long productId) {
        log.info("Performing advanced search with status: {} and productId: {}", status, productId);
        return advancedSearchOrdersUseCase.advancedSearchOrders(status, productId);
    }
}
