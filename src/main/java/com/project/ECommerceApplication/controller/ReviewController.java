package com.project.ECommerceApplication.controller;

import com.project.ECommerceApplication.model.ProductReview;
import com.project.ECommerceApplication.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ProductReview>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductReview>> getReviewsForProduct(@PathVariable Long productId) {
        List<ProductReview> reviews = reviewService.getReviewsForProduct(productId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<ProductReview> addReview(@RequestBody ProductReview review) {
        ProductReview savedReview = reviewService.addReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }
}
