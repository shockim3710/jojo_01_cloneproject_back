package com.cloneproject.ssgjojo.review.controller;

import com.cloneproject.ssgjojo.qna.dto.AnswerInputDto;
import com.cloneproject.ssgjojo.qna.dto.QnAOutputDto;
import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.ReviewDeleteDto;
import com.cloneproject.ssgjojo.review.dto.ReviewDto;
import com.cloneproject.ssgjojo.review.dto.ReviewEditDto;
import com.cloneproject.ssgjojo.review.dto.ReviewOutputDto;
import com.cloneproject.ssgjojo.review.service.IReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/review/addimg", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public boolean addReviewWithImg(@RequestParam("reviewPhoto") List<MultipartFile> reviewPhoto,
                                    @RequestPart ReviewDto reviewDto) {
        return iReviewService.addReviewWithImg(reviewDto, reviewPhoto);
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

    @GetMapping("/review/getAvgScore/{productId}")
    public Float getReviewAvgScore(@PathVariable Long productId) {
        return iReviewService.getReviewAvgScore(productId);
    }

    @GetMapping("/review/findAllByUser/{userId}")
    public List<ReviewOutputDto> findAllByUser(@PathVariable Long userId) {
        return iReviewService.findAllByUser(userId);
    }

    @GetMapping("/review/findAllByProduct/{productId}")
    public List<ReviewOutputDto> findAllByProduct(@PathVariable Long productId) {
        return iReviewService.findAllByProduct(productId);
    }
}
