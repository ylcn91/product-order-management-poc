package com.doksanbir.productordermanagementpoc.application.service.order;

import com.doksanbir.productordermanagementpoc.application.port.in.order.*;
import com.doksanbir.productordermanagementpoc.application.port.out.order.OrderRepositoryPort;
import com.doksanbir.productordermanagementpoc.application.specification.order.OrderSpecification;
import com.doksanbir.productordermanagementpoc.application.strategy.order.OrderProcessingStrategy;
import com.doksanbir.productordermanagementpoc.domain.Order;
import com.doksanbir.productordermanagementpoc.domain.OrderStatus;
import com.doksanbir.productordermanagementpoc.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service implementation for order-related use cases.
 * <p>
 * This class implements multiple use cases related to order management, including
 * creating, retrieving, updating, deleting, listing, searching, and advanced searching
 * for orders. The Strategy Pattern is used to process orders based on their current status.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements
        CreateOrderUseCase,
        RetrieveOrderUseCase,
        UpdateOrderUseCase,
        DeleteOrderUseCase,
        ListOrdersUseCase,
        SearchOrdersUseCase,
        AdvancedSearchOrdersUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final Map<OrderStatus, OrderProcessingStrategy> orderProcessingStrategies;

    /**
     * {@inheritDoc}
     * Additionally, the order is processed based on its status using the Strategy Pattern.
     */
    @Override
    public Order createOrder(Order order) {
        log.info("Creating order for product ID: {}", order.getProduct().getId());
        order.setStatus(OrderStatus.PENDING);
        Order savedOrder = orderRepositoryPort.save(order);
        processOrder(savedOrder);
        return savedOrder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Order retrieveOrder(Long orderId) {
        log.info("Retrieving order with ID: {}", orderId);
        return orderRepositoryPort.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Order updateOrder(Order order) {
        log.info("Updating order with ID: {}", order.getId());
        Order existingOrder = retrieveOrder(order.getId());
        existingOrder.setProduct(order.getProduct());
        existingOrder.setQuantity(order.getQuantity());
        existingOrder.setStatus(order.getStatus());
        Order updatedOrder = orderRepositoryPort.save(existingOrder);
        processOrder(updatedOrder);
        return updatedOrder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteOrder(Long orderId) {
        log.info("Deleting order with ID: {}", orderId);
        retrieveOrder(orderId); // Ensure order exists before deletion
        orderRepositoryPort.deleteById(orderId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Order> listOrders() {
        log.info("Listing all orders");
        return orderRepositoryPort.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Order> searchOrders(OrderStatus status) {
        log.info("Searching orders with status: {}", status);
        if (status != null) {
            return orderRepositoryPort.search(status);
        } else {
            return orderRepositoryPort.findAll();
        }
    }

    /**
     * Processes the order based on its current status using the Strategy Pattern.
     * The processing strategy is chosen based on the order's status, and the order is saved
     * again after processing.
     *
     * @param order the order to process
     */
    private void processOrder(Order order) {
        OrderStatus status = order.getStatus();
        OrderProcessingStrategy strategy = orderProcessingStrategies.get(status);
        if (strategy != null) {
            strategy.process(order);
            orderRepositoryPort.save(order);
        } else {
            log.warn("No processing strategy found for status: {}", status);
        }
    }

    /**
     * {@inheritDoc}
     * Additionally, performs an advanced search using the Specification Pattern.
     */
    @Override
    public List<Order> advancedSearchOrders(OrderStatus status, Long productId) {
        Specification<Order> spec = Specification.where(null);

        if (status != null) {
            spec = spec.and(OrderSpecification.hasStatus(status));
        }

        if (productId != null) {
            spec = spec.and(OrderSpecification.hasProductId(productId));
        }

        log.info("Performing advanced search with spec: {}", spec);
        return orderRepositoryPort.findAll(spec);
    }
}
