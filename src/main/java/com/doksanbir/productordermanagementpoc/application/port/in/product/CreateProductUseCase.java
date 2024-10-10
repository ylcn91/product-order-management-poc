package com.doksanbir.productordermanagementpoc.application.port.in.product;

import com.doksanbir.productordermanagementpoc.domain.Product;

/**
 * Use case interface for creating a new product.
 */
public interface CreateProductUseCase {
    /**
     * Creates a new product.
     *
     * @param product the product to create
     * @return the created product
     */
    Product createProduct(Product product);
}
