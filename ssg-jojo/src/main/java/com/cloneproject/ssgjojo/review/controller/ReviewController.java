package com.cloneproject.ssgjojo.review.controller;

import com.cloneproject.ssgjojo.qna.dto.AnswerInputDto;
import com.cloneproject.ssgjojo.qna.dto.QnAOutputDto;
import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.*;
import com.cloneproject.ssgjojo.review.repository.IReviewRepository;
import com.cloneproject.ssgjojo.review.service.IReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final IReviewRepository iReviewRepository;

    // 리뷰 작성
    @PostMapping("/review/add")
    public ReviewOutputDto addReview(@RequestBody ReviewDto reviewDto) {

        return iReviewService.addReview(reviewDto);
    }

    // 리뷰 작성 시 이미지 첨부
    @PostMapping(value = "/review/addimg", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public boolean addReviewWithImg(@RequestParam("reviewPhoto") List<MultipartFile> reviewPhoto,
                                    @RequestPart ReviewDto reviewDto) {
        return iReviewService.addReviewWithImg(reviewDto, reviewPhoto);
    }

    // 해당 상품의 리뷰 목록 조회
    @GetMapping("/review/findAllByProduct/{productId}")
    public List<ReviewOutputDto> findAllByProduct(@PathVariable Long productId) {
        return iReviewService.findAllByProduct(productId);
    }

    // 별점 높은 순, 별점 낮은 순, 최신 순으로 리뷰 정렬
    @GetMapping("/review/getAll/sort")
    public List<ReviewOutputDto> getByProductSort(@RequestParam Long productId, @RequestParam int sort) {
        return iReviewService.sortedGetReviewByProductId(productId, sort);
    }

    // 해당 유저가 작성한 리뷰 조회
    @GetMapping("/review/findAllByUser/{userId}")
    public List<ReviewOutputDto> findAllByUser(@PathVariable Long userId) {
        return iReviewService.findAllByUser(userId);
    }

    // 해당 유저가 작성 가능한 리뷰 조회
    @GetMapping("/review/possible/{userId}")
    public List<ReviewPossibleWriteDto> findPossibleReview(@PathVariable Long userId) {
        return iReviewService.findPossibleWrite(userId);
    }

    // 해당 유저가 작성한 리뷰 수정
    @PutMapping("/review/edit")
    public ReviewEditDto editReview(@RequestBody ReviewEditDto reviewEditDto) {
        return iReviewService.editReview(reviewEditDto);
    }

    // 해당 유저가 작성한 리뷰 삭제
    @DeleteMapping("/review/delete")
    public void deleteReview(@RequestBody ReviewDeleteDto reviewDeleteDto) {
        iReviewService.deleteReview(reviewDeleteDto);
    }

    // review 페이징
    @GetMapping("/review/paging/{productId}")
    public Page<Review> reviewPage(@RequestParam int page, @RequestParam int size, @PathVariable Long productId) {
        PageRequest pr = PageRequest.of(page, size);
        return iReviewService.pageList(pr, productId);

    }

}
