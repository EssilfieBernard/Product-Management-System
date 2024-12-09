package com.project.ECommerceApplication.controller;


import com.project.ECommerceApplication.model.Category;
import com.project.ECommerceApplication.model.Product;
import com.project.ECommerceApplication.response.ApiResponse;
import com.project.ECommerceApplication.service.ProductService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/paginated")
    public ResponseEntity<Page<Product>> getPaginatedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy) {

        Page<Product> products = productService.getPaginatedProducts(page, size, sortBy);
        return ResponseEntity.ok(products);
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse<>(true, "Products retrieved successfully", products, LocalDateTime.now()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Product retrieved successfully", product, LocalDateTime.now()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(new ApiResponse<>(true, "Product created successfully", savedProduct, LocalDateTime.now()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Product updated successfully",
                updatedProduct,
                LocalDateTime.now()
        ));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> deleteProduct(@PathVariable @NotNull Long id) {
        Product deletedProduct = productService.deleteProduct(id);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Product deleted successfully",
                deletedProduct,
                LocalDateTime.now()
        ));
    }

    @GetMapping("/prices")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsWithPriceBetween(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        List<Product> foundProducts = productService.getProductsWithPriceBetween(minPrice, maxPrice);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Products retrieved successfully",
                foundProducts,
                LocalDateTime.now()
        ));
    }

    @GetMapping("/minimum-price")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsWithPriceGreaterThan(@RequestParam Double price) {
        List<Product> foundProducts = productService.getProductsWithPriceGreaterThan(price);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Products retrieved successfully",
                foundProducts,
                LocalDateTime.now()
        ));
    }

    @GetMapping("/maximum-price")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsWithPriceLessThan(@RequestParam Double price) {
        List<Product> foundProducts = productService.getProductsWithPriceLessThan(price);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Products retrieved successfully",
                foundProducts,
                LocalDateTime.now()
        ));
    }


    @GetMapping("/category/{name}/paginated")
    public ResponseEntity<Page<Product>> getPaginatedProductsByCategory(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy) {

        Page<Product> products = productService.getPaginatedProductsByCategory(name, page, size, sortBy);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/all-products")
    public ResponseEntity<ApiResponse<Integer>> getNumberOfProductsInCategory(@RequestParam String categoryName) {
        Integer numberOfProducts = productService.getNumberOfProductsInCategory(categoryName);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Request made successfully",
                numberOfProducts,
                LocalDateTime.now()
        ));
    }

    @PostMapping("/category")
    public ResponseEntity<ApiResponse<Category>> createCategory(@RequestBody Category category) {
        var newCategory = productService.createCategory(category);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Category created successfully",
                newCategory,
                LocalDateTime.now()
        ));
    }

    @GetMapping("/category")
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories() {
        List<Category> categories = productService.getAllCategories();
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Categories retrieved successfully",
                categories,
                LocalDateTime.now()
        ));
    }

    @GetMapping("/category/{categoryName}/products")
    public ResponseEntity<ApiResponse<List<Product>>> getCategoryByName(@PathVariable String categoryName) {
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Products retrieved successfully",
                productService.getProductByCategory(categoryName),
                LocalDateTime.now()
        ));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable Long id) {
        var category = productService.getCategoryById(id);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Category retrieved successfully",
                category,
                LocalDateTime.now()
        ));
    }

}

// E-Commerce Application
//This project is a Spring Boot-based e-commerce application that provides APIs for managing products and categories, including features like pagination, sorting, custom queries, and integration with MongoDB for additional data storage. It also includes monitoring capabilities using Spring Actuator and custom configurations for the DispatcherServlet.
//Features
//Product and Category Management:
//Add, update, delete, and retrieve products and categories.
//Products are linked to categories using a @ManyToOne relationship.
//Pagination and Sorting:
//Retrieve paginated and sorted lists of products.
//Supports dynamic page size, page number, and sorting parameters via query parameters.
//Custom Queries:
//JPQL and native SQL queries for advanced data retrieval.
//Example: Find products by category name or count products in a specific category.
//MongoDB Integration:
//Stores product reviews in a NoSQL database (MongoDB).
//Provides APIs to add and retrieve reviews for products.
//Monitoring with Spring Actuator:
//Exposes endpoints for application health checks (/actuator/health), metrics (/actuator/metrics), and more.
//Custom DispatcherServlet Configuration:
//Routes all API requests through /api/*.
//Supports custom exception handling and request interceptors.
//Error Handling:
//Global exception handling with meaningful error messages.
//Handles serialization issues like circular references between Product and Category.
//Technologies Used
//Spring Boot: Framework for building the application.
//Spring Data JPA: For interacting with the relational database.
//Spring Data MongoDB: For managing NoSQL data storage.
//Spring Actuator: For monitoring and management endpoints.
//Hibernate: ORM tool for database interactions.
//Jackson: For JSON serialization/deserialization.
//Lombok: To reduce boilerplate code (e.g., getters/setters).
//H2 Database (In-Memory): For development/testing purposes (can be replaced with MySQL or PostgreSQL).
//Postman: For testing APIs.
