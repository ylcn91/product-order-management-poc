package com.doksanbir.productordermanagementpoc.exception;

/**
 * Exception thrown when a product is invalid.
 */
public class InvalidProductException extends RuntimeException {
    public InvalidProductException(String message) {
        super(message);
    }
}
