package com.doksanbir.productordermanagementpoc.application.port.in.product;

import com.doksanbir.productordermanagementpoc.domain.Product;

import java.util.List;

/**
 * Use case interface for searching products based on attributes.
 */
public interface SearchProductsUseCase {
    /**
     * Searches products based on the provided criteria.
     *
     * @param name     the name of the product
     * @param category the category of the product
     * @return the list of matching products
     */
    List<Product> searchProducts(String name, String category);
}
