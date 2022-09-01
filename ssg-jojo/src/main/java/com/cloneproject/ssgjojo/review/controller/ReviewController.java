package com.cloneproject.ssgjojo.review.controller;

import com.cloneproject.ssgjojo.qna.dto.AnswerInputDto;
import com.cloneproject.ssgjojo.qna.dto.QnAOutputDto;
import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.*;
import com.cloneproject.ssgjojo.review.repository.IReviewRepository;
import com.cloneproject.ssgjojo.review.service.IReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Log4j2
public class ReviewController {

    private final IReviewService iReviewService;
    private final IReviewRepository iReviewRepository;

    // 리뷰 작성
    @PostMapping("/review/add")
    public ReviewOutputDto addReview(@RequestBody ReviewDto reviewDto, HttpServletRequest request) {

        return iReviewService.addReview(reviewDto, request);
    }

    // 리뷰 작성 시 이미지 첨부
    @PostMapping(value = "/review/addimg", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public boolean addReviewWithImg(@RequestParam("reviewPhoto") List<MultipartFile> reviewPhoto,
                                    @RequestPart ReviewDto reviewDto, HttpServletRequest request) {
        return iReviewService.addReviewWithImg(reviewDto, reviewPhoto, request);
    }

    // 해당 상품의 리뷰 목록 조회
    @GetMapping("/review/findAllByProduct/{productId}")
    public List<ReviewOutputDto> findAllByProduct(@PathVariable Long productId) {
        return iReviewService.findAllByProduct(productId);
    }

    // 해당 유저가 작성한 리뷰 조회
    @GetMapping("/review/findAllByUser")
    public List<ReviewOutputDto> findAllByUser(HttpServletRequest request) {
        return iReviewService.findAllByUser(request);
    }

    // 해당 유저가 작성 가능한 리뷰 조회
    @GetMapping("/review/possible")
    public List<ReviewPossibleWriteDto> findPossibleReview(HttpServletRequest request) {
        return iReviewService.findPossibleWrite(request);
    }

    // 해당 유저가 작성한 리뷰 수정
    @PutMapping("/review/edit")
    public ReviewEditDto editReview(@RequestBody ReviewEditDto reviewEditDto, HttpServletRequest request) {
        return iReviewService.editReview(reviewEditDto, request);
    }

    // 해당 유저가 작성한 리뷰 삭제
    @DeleteMapping("/review/delete")
    public void deleteReview(@RequestBody ReviewDeleteDto reviewDeleteDto, HttpServletRequest request) {
        iReviewService.deleteReview(reviewDeleteDto, request);
    }

    @GetMapping("/review/paging/{productId}")
    public ResponseEntity<List<ReviewOutputDto>> reviewPage(@RequestParam(name = "page", defaultValue = "1") int page,
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

        return new ResponseEntity<>(reviewList, HttpStatus.OK);
    }
}
