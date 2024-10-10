package com.doksanbir.productordermanagementpoc.exception;

/**
 * Exception thrown when an order is invalid.
 */
public class InvalidOrderException extends RuntimeException {
    public InvalidOrderException(String message) {
        super(message);
    }
}
