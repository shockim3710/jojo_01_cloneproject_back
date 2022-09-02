package com.cloneproject.ssgjojo.review.service;

import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.*;
import com.cloneproject.ssgjojo.reviewphoto.domain.ReviewPhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IReviewService {

    ReviewOutputDto addReview(ReviewDto reviewDto, HttpServletRequest request);
    boolean addReviewWithImg(ReviewDto reviewDto, List<MultipartFile> reviewPhoto, HttpServletRequest request);
    ReviewEditDto editReview(ReviewEditDto reviewEditDto, HttpServletRequest request);
    List<ReviewOutputDto> findAllByProduct(Long productId);
    List<ReviewOutputDto> findAllByUser(HttpServletRequest request);
    List<ReviewPossibleWriteDto> findPossibleWrite(HttpServletRequest request);
    void deleteReview(ReviewDeleteDto reviewDeleteDto, HttpServletRequest request);
    List<ReviewOutputDto> pageList(Pageable pageable, Long productId);
}
