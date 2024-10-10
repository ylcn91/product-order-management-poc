package com.doksanbir.productordermanagementpoc.application.port.in.product;

/**
 * Use case interface for deleting a product.
 */
public interface DeleteProductUseCase {
    /**
     * Deletes a product by its ID.
     *
     * @param productId the ID of the product to delete
     */
    void deleteProduct(Long productId);
}
