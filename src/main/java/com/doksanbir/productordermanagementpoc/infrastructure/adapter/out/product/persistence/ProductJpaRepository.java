package com.doksanbir.productordermanagementpoc.infrastructure.adapter.out.product.persistence;

import com.doksanbir.productordermanagementpoc.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for Product entity.
 */
@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    /**
     * Finds products by name containing and category containing.
     *
     * @param name     the name to search for
     * @param category the category to search for
     * @return the list of matching products
     */
    List<Product> findByNameContainingAndCategoryContaining(String name, String category);
}
