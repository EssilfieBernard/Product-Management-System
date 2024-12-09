package com.project.ECommerceApplication.repository;

import com.project.ECommerceApplication.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
