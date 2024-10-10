package com.doksanbir.productordermanagementpoc.domain;

import com.doksanbir.productordermanagementpoc.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Represents an order in the system.
 */
@Entity
@Table(name = "orders")
@EntityListeners(OrderEntityListener.class)
@Getter
@Setter
@Slf4j
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;
}
