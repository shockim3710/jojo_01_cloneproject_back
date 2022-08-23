package com.cloneproject.ssgjojo.review.service;

import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.ReviewDeleteDto;
import com.cloneproject.ssgjojo.review.dto.ReviewDto;
import com.cloneproject.ssgjojo.review.dto.ReviewEditDto;
import com.cloneproject.ssgjojo.review.dto.ReviewOutputDto;
import com.cloneproject.ssgjojo.reviewphoto.domain.ReviewPhoto;

import java.util.List;

public interface IReviewService {

    Review addReview(ReviewDto reviewDto);
//    ReviewPhoto addReviewWithImg();
    ReviewEditDto editReview(ReviewEditDto reviewEditDto);
    List<ReviewOutputDto> getReviewByProductId(Long id);
    List<ReviewOutputDto> sortedGetReviewByProductId(Long id, int sort);
    List<Review> getAllReview();
    Integer getReviewCountByProduct(Long productId);
    void deleteReview(ReviewDeleteDto reviewDeleteDto);

    List<ReviewOutputDto> getTop5(Long productId);

    Float getReviewAvgScore(Long id);

    List<ReviewOutputDto> findAllByUser(Long userId);


}
