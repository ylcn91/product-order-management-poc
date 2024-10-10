package com.doksanbir.productordermanagementpoc.application.strategy.order;

import com.doksanbir.productordermanagementpoc.domain.Order;
import com.doksanbir.productordermanagementpoc.domain.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Strategy for processing pending orders.
 */
@Component
@Slf4j
public class PendingOrderProcessingStrategy implements OrderProcessingStrategy {

    @Override
    public void process(Order order) {
        log.info("Processing pending order with ID: {}", order.getId());
        order.setStatus(OrderStatus.CONFIRMED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderStatus getHandledStatus() {
        return OrderStatus.PENDING;
    }
}
