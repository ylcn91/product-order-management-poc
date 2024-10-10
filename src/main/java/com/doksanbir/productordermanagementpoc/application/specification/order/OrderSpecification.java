package com.doksanbir.productordermanagementpoc.application.specification.order;

import com.doksanbir.productordermanagementpoc.domain.Order;
import com.doksanbir.productordermanagementpoc.domain.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

/**
 * Utility class for creating Order Specifications.
 * <p>
 * This class provides reusable JPA specifications for querying {@link Order} entities based on
 * various criteria such as order status and product ID.
 */
public class OrderSpecification {

    /**
     * Creates a specification to filter orders by their status.
     *
     * @param status the {@link OrderStatus} to filter by
     * @return a {@link Specification} to filter orders by status
     */
    public static Specification<Order> hasStatus(OrderStatus status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }

    /**
     * Creates a specification to filter orders by the product ID associated with the order.
     *
     * @param productId the product ID to filter by
     * @return a {@link Specification} to filter orders by product ID
     */
    public static Specification<Order> hasProductId(Long productId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("product").get("id"), productId);
    }
}