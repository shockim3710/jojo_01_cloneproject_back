package com.cloneproject.ssgjojo.review.service;

import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.ReviewDeleteDto;
import com.cloneproject.ssgjojo.review.dto.ReviewDto;
import com.cloneproject.ssgjojo.review.dto.ReviewEditDto;

import java.util.List;

public interface IReviewService {

    Review addReview(ReviewDto reviewDto);
    Review editReview(ReviewEditDto reviewEditDto);
    List<Review> getReviewById(Long id);
    List<Review> getAllReview();

    void deleteReview(ReviewDeleteDto reviewDeleteDto);
}
