package com.cloneproject.ssgjojo.review.service;

import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.ReviewDeleteDto;
import com.cloneproject.ssgjojo.review.dto.ReviewDto;
import com.cloneproject.ssgjojo.review.dto.ReviewEditDto;
import com.cloneproject.ssgjojo.review.dto.ReviewOutputDto;

import java.util.List;

public interface IReviewService {

    Review addReview(ReviewDto reviewDto);
    ReviewEditDto editReview(ReviewEditDto reviewEditDto);
    List<ReviewOutputDto> getReviewByProductId(Long id);
    List<Review> getAllReview();

    void deleteReview(ReviewDeleteDto reviewDeleteDto);
}
