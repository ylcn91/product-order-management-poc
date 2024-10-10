package com.doksanbir.productordermanagementpoc.application.port.in.product;

import com.doksanbir.productordermanagementpoc.domain.Product;

import java.util.List;

/**
 * Use case interface for listing all products with optional filtering and pagination.
 */
public interface ListProductsUseCase {
    /**
     * Lists all products with optional filtering.
     *
     * @return the list of products
     */
    List<Product> listProducts();
}
