package com.project.ECommerceApplication.service;

import com.project.ECommerceApplication.model.ProductReview;
import com.project.ECommerceApplication.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ProductReviewRepository reviewRepository;

    public List<ProductReview> getReviewsForProduct(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    public ProductReview addReview(ProductReview review) {
        return reviewRepository.save(review);
    }

    public List<ProductReview> getAllReviews() {
        return reviewRepository.findAll();
    }
}
