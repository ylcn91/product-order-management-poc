package com.doksanbir.productordermanagementpoc.application.service.product;

import com.doksanbir.productordermanagementpoc.application.port.in.product.*;
import com.doksanbir.productordermanagementpoc.application.port.out.product.ProductRepositoryPort;
import com.doksanbir.productordermanagementpoc.application.specification.product.ProductSpecification;
import com.doksanbir.productordermanagementpoc.domain.Product;
import com.doksanbir.productordermanagementpoc.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for product-related use cases.
 * <p>
 * This class implements multiple use cases related to product management, including
 * creating, retrieving, updating, deleting, listing, stock adjustment, and searching
 * for products.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements
        CreateProductUseCase,
        RetrieveProductUseCase,
        UpdateProductUseCase,
        DeleteProductUseCase,
        ListProductsUseCase,
        AdjustStockUseCase,
        SearchProductsUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    /**
     * {@inheritDoc}
     */
    @Override
    public Product createProduct(Product product) {
        log.info("Creating product: {}", product.getName());
        return productRepositoryPort.save(product);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product retrieveProduct(Long productId) {
        log.info("Retrieving product with ID: {}", productId);
        return productRepositoryPort.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product updateProduct(Product product) {
        log.info("Updating product with ID: {}", product.getId());
        Product existingProduct = retrieveProduct(product.getId());
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        return productRepositoryPort.save(existingProduct);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteProduct(Long productId) {
        log.info("Deleting product with ID: {}", productId);
        retrieveProduct(productId);
        productRepositoryPort.deleteById(productId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> listProducts() {
        log.info("Listing all products");
        return productRepositoryPort.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void adjustStock(Long productId, int quantity) {
        log.info("Adjusting stock for product ID: {} by {}", productId, quantity);
        Product product = retrieveProduct(productId);
        product.adjustStock(quantity);
        productRepositoryPort.save(product);
    }

    /**
     * {@inheritDoc}
     * Additionally, performs a product search using the Specification Pattern.
     */
    @Override
    public List<Product> searchProducts(String name, String category) {
        log.info("Searching products with name: {} and category: {}", name, category);
        if (name != null || category != null) {
            Specification<Product> spec = Specification.where(null);

            if (name != null) {
                spec = spec.and(ProductSpecification.hasNameContaining(name));
            }

            if (category != null) {
                spec = spec.and(ProductSpecification.hasCategoryContaining(category));
            }

            log.info("Performing product search with spec: {}", spec);
            return productRepositoryPort.findAll(spec);
        } else {
            return productRepositoryPort.findAll();
        }
    }
}
