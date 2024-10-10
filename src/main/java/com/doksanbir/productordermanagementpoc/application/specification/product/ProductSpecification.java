package com.doksanbir.productordermanagementpoc.application.specification.product;

import com.doksanbir.productordermanagementpoc.domain.Product;
import org.springframework.data.jpa.domain.Specification;

/**
 * Utility class for creating Product Specifications.
 * <p>
 * This class provides reusable JPA specifications for querying {@link Product} entities based on
 * various criteria such as product name and category.
 */
public class ProductSpecification {

    /**
     * Creates a specification to filter products by a name substring, case-insensitive.
     *
     * @param name the substring to search for in the product name
     * @return a {@link Specification} to filter products by name
     */
    public static Specification<Product> hasNameContaining(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    /**
     * Creates a specification to filter products by a category substring, case-insensitive.
     *
     * @param category the substring to search for in the product category
     * @return a {@link Specification} to filter products by category
     */
    public static Specification<Product> hasCategoryContaining(String category) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("category")), "%" + category.toLowerCase() + "%");
    }
}