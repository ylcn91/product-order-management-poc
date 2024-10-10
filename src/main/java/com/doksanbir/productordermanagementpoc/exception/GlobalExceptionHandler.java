package com.doksanbir.productordermanagementpoc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler to manage application exceptions uniformly.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles OrderNotFoundException.
     *
     * @param ex the exception
     * @return response entity with error details
     */
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * Handles ProductNotFoundException.
     *
     * @param ex the exception
     * @return response entity with error details
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * Handles InvalidOrderException.
     *
     * @param ex the exception
     * @return response entity with error details
     */
    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<Object> handleInvalidOrderException(InvalidOrderException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * Handles InvalidProductException.
     *
     * @param ex the exception
     * @return response entity with error details
     */
    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<Object> handleInvalidProductException(InvalidProductException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * Handles validation errors.
     *
     * @param ex the exception
     * @return response entity with error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other exceptions.
     *
     * @param ex the exception
     * @return response entity with error details
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
    }

    /**
     * Builds the response entity with error details.
     *
     * @param status  the HTTP status
     * @param message the error message
     * @return the response entity
     */
    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
