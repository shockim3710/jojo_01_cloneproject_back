package com.cloneproject.ssgjojo.review.controller;

import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.ReviewDeleteDto;
import com.cloneproject.ssgjojo.review.dto.ReviewDto;
import com.cloneproject.ssgjojo.review.dto.ReviewEditDto;
import com.cloneproject.ssgjojo.review.dto.ReviewOutputDto;
import com.cloneproject.ssgjojo.review.service.IReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReviewController {

    private final IReviewService iReviewService;

    @PostMapping("/review/add")
    public Review addReview(@RequestBody ReviewDto reviewDto) {

        return iReviewService.addReview(reviewDto);
    }

    @GetMapping("/review/getAll")
    public List<Review> getAllReview() {

        return iReviewService.getAllReview();
    }

    @PutMapping("/review/edit")
    public ReviewEditDto editReview(@RequestBody ReviewEditDto reviewEditDto) {
        return iReviewService.editReview(reviewEditDto);
    }

    @DeleteMapping("/review/delete")
    public void deleteReview(@RequestBody ReviewDeleteDto reviewDeleteDto) {
        iReviewService.deleteReview(reviewDeleteDto);
    }

    @GetMapping("/review/{id}")
    public List<ReviewOutputDto> getReviewById(@PathVariable Long id) {
        return iReviewService.getReviewByProductId(id);
    }

    @GetMapping("/review/count/{productId}")
    public Integer getReviewCountByProduct(@PathVariable Long productId) {
        return iReviewService.getReviewCountByProduct(productId);
    }

    @GetMapping("/review/get5/{productId}")
    public List<ReviewOutputDto> getTop(@PathVariable Long productId) {
        return iReviewService.getTop5(productId);
    }

    @GetMapping("/review/getAll/sort")
    public List<ReviewOutputDto> getByProductSort(@RequestParam Long productId, @RequestParam int sort) {
        return iReviewService.sortedGetReviewByProductId(productId, sort);
    }

}
