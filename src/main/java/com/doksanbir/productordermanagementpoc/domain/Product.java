package com.doksanbir.productordermanagementpoc.domain;

import com.doksanbir.productordermanagementpoc.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Represents a product in the inventory.
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@Slf4j
public class Product extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stockQuantity;

    private String category;

    /**
     * Adjusts the stock quantity of the product.
     *
     * @param quantity the amount to adjust the stock by
     */
    public void adjustStock(int quantity) {
        this.stockQuantity += quantity;
        log.info("Stock adjusted by {}. New stock: {}", quantity, this.stockQuantity);
    }
}
