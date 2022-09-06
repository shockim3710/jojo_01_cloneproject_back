package com.cloneproject.ssgjojo.review.controller;

import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.*;
import com.cloneproject.ssgjojo.review.service.IReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Log4j2
public class ReviewController {

    private final IReviewService iReviewService;

    // 리뷰 작성
    @PostMapping("/review/add")
    public ResponseEntity<?> addReview(@RequestBody ReviewDto reviewDto, HttpServletRequest request) {
        Review review = iReviewService.addReview(reviewDto, request);

        if(review!=null){
            return ResponseEntity.status(200).body("리뷰가 등록되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    // 리뷰 작성 시 이미지 첨부
    @PostMapping(value = "/review/addimg", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addReviewWithImg(@RequestParam("reviewPhoto") List<MultipartFile> reviewPhoto,
                                    @RequestPart ReviewDto reviewDto, HttpServletRequest request) {
        boolean review = iReviewService.addReviewWithImg(reviewDto, reviewPhoto, request);

        if(review == true){
            return ResponseEntity.status(200).body("이미지가 첨부되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    // 해당 상품의 리뷰 목록 조회
    @GetMapping("/review/findAllByProduct/{productId}")
    public ResponseEntity<?> findAllByProduct(@PathVariable Long productId) {
        List<ReviewOutputDto> review = iReviewService.findAllByProduct(productId);

        if(review!=null){
            return ResponseEntity.status(200).body(review);
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    // 해당 유저가 작성한 리뷰 조회
    @GetMapping("/review/findAllByUser")
    public ResponseEntity<?> findAllByUser(HttpServletRequest request) {
        List<ReviewOutputDto> review = iReviewService.findAllByUser(request);

        if(review!=null){
            return ResponseEntity.status(200).body(review);
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    // 해당 유저가 작성 가능한 리뷰 조회
    @GetMapping("/review/possible")
    public ResponseEntity<?> findPossibleReview(HttpServletRequest request) {
        List<ReviewPossibleWriteDto> review = iReviewService.findPossibleWrite(request);

        if(review!=null){
            return ResponseEntity.status(200).body(review);
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    // 리뷰 페이징
    @GetMapping("/review/paging/{productId}")
    public ResponseEntity<?> reviewPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                                            @RequestParam(name = "size", defaultValue = "10") int size,
                                                            @RequestParam(name = "sort", defaultValue = "3") int sort,
                                                            @PathVariable Long productId) {


        Pageable pr = PageRequest.of(page - 1, size, Sort.by("id").descending());
        if(sort == 1)
            pr = PageRequest.of(page - 1, size, Sort.by("score").ascending().and(Sort.by("id").descending()));
        else if(sort == 2)
            pr = PageRequest.of(page - 1, size, Sort.by("score").descending().and(Sort.by("id").descending()));

        log.info("page = {} , size = {}", page, size);

        List<ReviewOutputDto> reviewList = iReviewService.pageList(pr, productId);

        if(reviewList!=null){
            return ResponseEntity.status(200).body(reviewList);
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    // 해당 유저가 작성한 리뷰 수정
    @PutMapping("/review/edit")
    public ResponseEntity<?> editReview(@RequestBody ReviewEditDto reviewEditDto, HttpServletRequest request) {
        Review review = iReviewService.editReview(reviewEditDto, request);

        if(review!=null){
            return ResponseEntity.status(200).body("리뷰가 수정되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    // 해당 유저가 작성한 리뷰 삭제
    @DeleteMapping("/review/delete")
    public ResponseEntity<?> deleteReview(@RequestBody ReviewDeleteDto reviewDeleteDto, HttpServletRequest request) {
        Optional<Review> review = iReviewService.deleteReview(reviewDeleteDto, request);

        if(review.isPresent()){
            return ResponseEntity.status(200).body("리뷰가 삭제되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }


}
