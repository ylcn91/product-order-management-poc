package com.doksanbir.productordermanagementpoc.exception;

/**
 * Exception thrown when a product is not found.
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("Product not found with ID: " + productId);
    }
}
