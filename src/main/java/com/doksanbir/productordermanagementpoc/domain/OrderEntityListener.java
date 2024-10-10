package com.doksanbir.productordermanagementpoc.domain;

import jakarta.persistence.PrePersist;
import lombok.extern.slf4j.Slf4j;

/**
 * Entity listener for Order to handle lifecycle events.
 */
@Slf4j
public class OrderEntityListener {

    /**
     * Sets the initial status of the order before persisting.
     *
     * @param order the order entity
     */
    @PrePersist
    public void prePersist(Order order) {
        if (order.getStatus() == null) {
            order.setStatus(OrderStatus.PENDING);
            log.info("Order status set to PENDING");
        }
    }
}
