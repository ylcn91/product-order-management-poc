package com.doksanbir.productordermanagementpoc.application.port.out.product;

import com.doksanbir.productordermanagementpoc.domain.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * Repository port interface for product persistence operations.
 */
public interface ProductRepositoryPort {
    /**
     * Saves a product.
     *
     * @param product the product to save
     * @return the saved product
     */
    Product save(Product product);

    /**
     * Finds a product by its ID.
     *
     * @param productId the ID of the product
     * @return an optional containing the product if found
     */
    Optional<Product> findById(Long productId);

    /**
     * Deletes a product by its ID.
     *
     * @param productId the ID of the product to delete
     */
    void deleteById(Long productId);

    /**
     * Retrieves all products.
     *
     * @return the list of products
     */
    List<Product> findAll();

    /**
     * Searches products based on name and category.
     *
     * @param name     the name of the product
     * @param category the category of the product
     * @return the list of matching products
     */
    List<Product> search(String name, String category);

    /**
     * Finds products based on specifications.
     *
     * @param specification the specification to filter products
     * @return the list of matching products
     */
    List<Product> findAll(Specification<Product> specification);
}
