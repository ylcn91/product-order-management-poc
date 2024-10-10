package com.doksanbir.productordermanagementpoc.application.strategy.order;

import com.doksanbir.productordermanagementpoc.domain.Order;
import com.doksanbir.productordermanagementpoc.domain.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Strategy for processing confirmed orders.
 */
@Component
@Slf4j
public class ConfirmedOrderProcessingStrategy implements OrderProcessingStrategy {

    @Override
    public void process(Order order) {
        log.info("Processing confirmed order with ID: {}", order.getId());
        order.setStatus(OrderStatus.SHIPPED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderStatus getHandledStatus() {
        return OrderStatus.CONFIRMED;
    }
}
