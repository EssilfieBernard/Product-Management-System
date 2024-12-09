package com.project.ECommerceApplication.service;

import com.project.ECommerceApplication.exception.ResourceNotFoundException;
import com.project.ECommerceApplication.exception.ValidationException;
import com.project.ECommerceApplication.model.Category;
import com.project.ECommerceApplication.model.CategoryBinaryTree;
import com.project.ECommerceApplication.model.Product;
import com.project.ECommerceApplication.repository.CategoryRepository;
import com.project.ECommerceApplication.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryBinaryTree categoryBinaryTree;

    @PostConstruct
    public void initializeCategoryTree() {
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories)
            categoryBinaryTree.insertCategory(category);
    }

    public Page<Product> getPaginatedProducts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return productRepository.findAll(pageable);
    }

    public Page<Product> getPaginatedProductsByCategory(String categoryName, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return productRepository.findByCategoryName(categoryName, pageable);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    public Product saveProduct(Product product) {
        validateProduct(product);
        validateCategory(product.getCategory().getId());
        var savedProduct = productRepository.save(product);
        initializeCategoryTree();
        return savedProduct;
    }

    public Product updateProduct(Long id, Product product) {
        var productToUpdate = getProductById(id);

        validateProduct(product);
        validateCategory(product.getCategory().getId());

        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        var savedProduct = productRepository.save(productToUpdate);
        initializeCategoryTree();

        return savedProduct;

    }

    public Product deleteProduct(Long id) {
        var productToDelete = getProductById(id);

        productRepository.delete(productToDelete);
        initializeCategoryTree();
        return productToDelete;
    }

    public List<Product> getProductsWithPriceBetween(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceRange(minPrice, maxPrice);
    }

    public List<Product> getProductsWithPriceGreaterThan(Double price) {
        return productRepository.findProductsByPriceGreaterThan(price);
    }

    public List<Product> getProductsWithPriceLessThan(Double price) {
        return productRepository.findProductsByPriceLessThan(price);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category) {
        validateCategory(category);
        var savedCategory = categoryRepository.save(category);
        initializeCategoryTree();

        return savedCategory;
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
    }

    public Category getCategoryByName(String categoryName) {
        return categoryBinaryTree.searchCategory(categoryName);
    }

    public Integer getNumberOfProductsInCategory(String categoryName) {
        var category = getCategoryByName(categoryName);
        if (category.getId() == null)
            throw new ResourceNotFoundException("Category with id " + category.getId() + " not found");

        return productRepository.countProductsInCategory(category.getId());
    }

    public List<Product> getProductByCategory(String categoryName) {
        var category = getCategoryByName(categoryName);

        if (category != null)
            return productRepository.findAllByCategory(category); // Assuming this method exists in ProductRepository

        throw new ResourceNotFoundException("Category not found with name " + categoryName);
    }

    private void validateProduct(Product product) {
        List<String> errors = new ArrayList<>();

        if (product.getName() == null || product.getName().isEmpty())
            errors.add("Product name is required");

        if (product.getPrice() == null || product.getPrice() <= 0)
            errors.add("Price must be greater than zero");

        if (product.getCategory() == null || product.getCategory().getId() == null)
            errors.add("Category is required");

        if (!errors.isEmpty())
            throw new ValidationException(String.join(", ", errors));
    }

    private void validateCategory(Category category) {
        if (category.getName() == null || category.getName().isEmpty())
            throw new ValidationException("Category name is required");
    }

    private void validateCategory(Long id) {
        if (!categoryRepository.existsById(id))
            throw new ResourceNotFoundException("Category not found with id: " + id);
    }

}
