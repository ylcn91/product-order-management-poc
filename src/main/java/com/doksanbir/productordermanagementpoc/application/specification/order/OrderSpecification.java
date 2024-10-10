package com.doksanbir.productordermanagementpoc.application.specification.order;

import com.doksanbir.productordermanagementpoc.domain.Order;
import org.springframework.data.jpa.domain.Specification;

/**
 * Utility class for creating Order Specifications.
 */
public class OrderSpecification {

    public static Specification<Order> hasStatus(com.doksanbir.productordermanagementpoc.domain.OrderStatus status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Order> hasProductId(Long productId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("product").get("id"), productId);
    }

}
