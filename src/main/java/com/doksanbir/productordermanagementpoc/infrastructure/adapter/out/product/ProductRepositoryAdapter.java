package com.doksanbir.productordermanagementpoc.infrastructure.adapter.out.product;

import com.doksanbir.productordermanagementpoc.application.port.out.product.ProductRepositoryPort;
import com.doksanbir.productordermanagementpoc.domain.Product;
import com.doksanbir.productordermanagementpoc.infrastructure.adapter.out.product.persistence.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Adapter implementation for product repository using Spring Data JPA.
 * <p>
 * This class implements the {@link ProductRepositoryPort} interface and provides
 * an adapter for interacting with the underlying database using Spring Data JPA.
 */
@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository productJpaRepository;

    /**
     * Saves a product to the database.
     *
     * @param product the product to save
     * @return the saved product
     */
    @Override
    public Product save(Product product) {
        return productJpaRepository.save(product);
    }

    /**
     * Finds a product by its ID.
     *
     * @param productId the ID of the product
     * @return an optional containing the product if found
     */
    @Override
    public Optional<Product> findById(Long productId) {
        return productJpaRepository.findById(productId);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId the ID of the product to delete
     */
    @Override
    public void deleteById(Long productId) {
        productJpaRepository.deleteById(productId);
    }

    /**
     * Retrieves all products from the database.
     *
     * @return a list of all products
     */
    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll();
    }

    /**
     * Searches products by their name and category.
     *
     * @param name     the name to search for
     * @param category the category to search for
     * @return a list of products matching the name and category criteria
     */
    @Override
    public List<Product> search(String name, String category) {
        return productJpaRepository.findByNameContainingAndCategoryContaining(name, category);
    }

    /**
     * Finds products based on specifications.
     *
     * @param specification the specification to filter products
     * @return a list of products matching the given specification
     */
    @Override
    public List<Product> findAll(Specification<Product> specification) {
        return productJpaRepository.findAll(specification);
    }
}