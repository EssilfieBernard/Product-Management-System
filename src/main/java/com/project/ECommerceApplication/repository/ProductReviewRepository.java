package com.project.ECommerceApplication.repository;

import com.project.ECommerceApplication.model.ProductReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductReviewRepository extends MongoRepository<ProductReview, Integer> {

    List<ProductReview> findByProductId(Long productId);

}
