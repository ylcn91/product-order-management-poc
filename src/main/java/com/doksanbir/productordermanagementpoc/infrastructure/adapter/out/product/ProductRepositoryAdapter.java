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
 */
@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product save(Product product) {
        return productJpaRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return productJpaRepository.findById(productId);
    }

    @Override
    public void deleteById(Long productId) {
        productJpaRepository.deleteById(productId);
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll();
    }

    @Override
    public List<Product> search(String name, String category) {
        return productJpaRepository.findByNameContainingAndCategoryContaining(name, category);
    }

    @Override
    public List<Product> findAll(Specification<Product> specification) {
        return productJpaRepository.findAll(specification);
    }
}
