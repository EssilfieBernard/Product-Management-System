package com.project.ECommerceApplication.repository;

import com.project.ECommerceApplication.model.Category;
import com.project.ECommerceApplication.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);

    // Find all products with pagination and sorting
    Page<Product> findAll(Pageable pageable);

    // Find products by category name with pagination and sorting
    @Query("SELECT p FROM Product p WHERE p.category.name = :categoryName")
    Page<Product> findByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findByPriceRange(@Param("minPrice") Double minPrice,
                                   @Param("maxPrice") Double maxPrice);

    @Query("SELECT p FROM Product p WHERE p.category.name = :categoryName")
    List<Product> findProductsByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findProductsByPriceGreaterThan(@Param("price") Double price);

    @Query("SELECT p FROM Product p WHERE p.price < :price")
    List<Product> findProductsByPriceLessThan(@Param("price") Double price);

    @Query(value = "SELECT COUNT(*) FROM product WHERE category_id = :categoryId", nativeQuery = true)
    Integer countProductsInCategory(@Param("categoryId") Long categoryId);


}
