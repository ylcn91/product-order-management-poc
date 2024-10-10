package com.doksanbir.productordermanagementpoc.application.port.in.product;

import com.doksanbir.productordermanagementpoc.domain.Product;

/**
 * Use case interface for retrieving a product by ID.
 */
public interface RetrieveProductUseCase {
    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product
     * @return the retrieved product
     */
    Product retrieveProduct(Long productId);
}
