package com.cloneproject.ssgjojo.review.service;

import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.ReviewDto;

import java.util.List;

public interface IReviewService {

    Review addReview(ReviewDto reviewDto);
    Review editReview(Review review);
    Review getReviewById(Long id);
    List<Review> getAllReview();

    void deleteReview(Long id);
}
