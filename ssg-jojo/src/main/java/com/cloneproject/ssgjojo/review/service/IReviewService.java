package com.cloneproject.ssgjojo.review.service;

import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.*;
import com.cloneproject.ssgjojo.reviewphoto.domain.ReviewPhoto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IReviewService {

    ReviewOutputDto addReview(ReviewDto reviewDto);
    boolean addReviewWithImg(ReviewDto reviewDto, List<MultipartFile> reviewPhoto);
    ReviewEditDto editReview(ReviewEditDto reviewEditDto);
    List<ReviewOutputDto> sortedGetReviewByProductId(Long id, int sort);
    List<ReviewOutputDto> findAllByProduct(Long productId);
    void deleteReview(ReviewDeleteDto reviewDeleteDto);

//    Integer getReviewCountByProduct(Long productId);

//    List<ReviewOutputDto> getTop5(Long productId);

//    Float getReviewAvgScore(Long id);

    List<ReviewOutputDto> findAllByUser(Long userId);

    List<ReviewPossibleWriteDto> findPossibleWrite(Long userId);


    void test();
}
