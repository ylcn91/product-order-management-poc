package com.doksanbir.productordermanagementpoc.infrastructure.adapter.in.product;

import com.doksanbir.productordermanagementpoc.application.port.in.product.*;
import com.doksanbir.productordermanagementpoc.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing products.
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final RetrieveProductUseCase retrieveProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final AdjustStockUseCase adjustStockUseCase;
    private final SearchProductsUseCase searchProductsUseCase;

    /**
     * Creates a new product.
     *
     * @param product the product to create
     * @return the created product
     */
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        log.info("Creating product: {}", product.getName());
        return createProductUseCase.createProduct(product);
    }

    /**
     * Retrieves a product by ID.
     *
     * @param productId the ID of the product
     * @return the retrieved product
     */
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
        log.info("Retrieving product with ID: {}", productId);
        return retrieveProductUseCase.retrieveProduct(productId);
    }

    /**
     * Updates an existing product.
     *
     * @param product the product with updated information
     * @return the updated product
     */
    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        log.info("Updating product with ID: {}", product.getId());
        return updateProductUseCase.updateProduct(product);
    }

    /**
     * Deletes a product by ID.
     *
     * @param productId the ID of the product to delete
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) {
        log.info("Deleting product with ID: {}", productId);
        deleteProductUseCase.deleteProduct(productId);
    }

    /**
     * Lists all products.
     *
     * @return the list of products
     */
    @GetMapping
    public List<Product> listProducts() {
        log.info("Listing all products");
        return listProductsUseCase.listProducts();
    }

    /**
     * Adjusts the stock of a product.
     *
     * @param productId the ID of the product
     * @param quantity  the quantity to adjust by
     */
    @PostMapping("/{id}/adjust-stock")
    public void adjustStock(@PathVariable("id") Long productId, @RequestParam int quantity) {
        log.info("Adjusting stock for product ID: {} by {}", productId, quantity);
        adjustStockUseCase.adjustStock(productId, quantity);
    }

    /**
     * Searches for products based on name and category.
     *
     * @param name     the name of the product
     * @param category the category of the product
     * @return the list of matching products
     */
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String category) {
        log.info("Searching products with name: {} and category: {}", name, category);
        return searchProductsUseCase.searchProducts(name, category);
    }
}
