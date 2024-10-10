package com.doksanbir.productordermanagementpoc.application.port.in.product;

import com.doksanbir.productordermanagementpoc.domain.Product;

/**
 * Use case interface for updating an existing product.
 */
public interface UpdateProductUseCase {
    /**
     * Updates an existing product.
     *
     * @param product the product with updated information
     * @return the updated product
     */
    Product updateProduct(Product product);
}
