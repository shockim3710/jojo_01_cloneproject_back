package com.cloneproject.ssgjojo.review.controller;

import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.ReviewDeleteDto;
import com.cloneproject.ssgjojo.review.dto.ReviewDto;
import com.cloneproject.ssgjojo.review.dto.ReviewEditDto;
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
    public Review editReview(@RequestBody ReviewEditDto reviewEditDto) {
        return iReviewService.editReview(reviewEditDto);
    }

    @DeleteMapping("/review/delete")
    public void deleteReview(@RequestBody ReviewDeleteDto reviewDeleteDto) {
        iReviewService.deleteReview(reviewDeleteDto);
    }

    @GetMapping("/review/{id}")
    public List<Review> getReviewById(@PathVariable Long id) {
        return iReviewService.getReviewById(id);
    }
}
