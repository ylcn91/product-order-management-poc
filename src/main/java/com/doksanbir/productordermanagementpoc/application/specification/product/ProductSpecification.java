package com.doksanbir.productordermanagementpoc.application.specification.product;

import com.doksanbir.productordermanagementpoc.domain.Product;
import org.springframework.data.jpa.domain.Specification;

/**
 * Utility class for creating Product Specifications.
 */
public class ProductSpecification {

    public static Specification<Product> hasNameContaining(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Product> hasCategoryContaining(String category) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("category")), "%" + category.toLowerCase() + "%");
    }

}
