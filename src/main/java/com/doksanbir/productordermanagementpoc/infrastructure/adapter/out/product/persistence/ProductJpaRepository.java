package com.doksanbir.productordermanagementpoc.infrastructure.adapter.out.product.persistence;

import com.doksanbir.productordermanagementpoc.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the {@link Product} entity.
 * <p>
 * Provides CRUD operations and custom queries for the product entity.
 * <p>
 * Extends {@link JpaSpecificationExecutor} to enable the execution of dynamic queries using the Specification pattern.
 *
 * <p>{@link JpaSpecificationExecutor} allows dynamic query construction at runtime. By using specifications, complex
 * queries can be built in a type-safe manner and combined using logical operators (e.g., AND, OR). This enables
 * separation of query logic from repository methods and supports reusable query conditions that can be combined
 * as needed. The Specification pattern is particularly useful when query parameters are not known upfront and
 * need to be constructed dynamically.
 */
@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    /**
     * Finds products by name and category.
     * <p>
     * Performs a search based on partial name and category match.
     *
     * @param name     the name to search for
     * @param category the category to search for
     * @return a list of products matching the name and category criteria
     */
    List<Product> findByNameContainingAndCategoryContaining(String name, String category);
}