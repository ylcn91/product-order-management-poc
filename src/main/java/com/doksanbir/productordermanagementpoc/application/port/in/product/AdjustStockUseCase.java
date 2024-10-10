package com.doksanbir.productordermanagementpoc.application.port.in.product;

/**
 * Use case interface for adjusting stock levels of a product.
 */
public interface AdjustStockUseCase {
    /**
     * Adjusts the stock of a product.
     *
     * @param productId the ID of the product
     * @param quantity  the quantity to adjust by
     */
    void adjustStock(Long productId, int quantity);
}
