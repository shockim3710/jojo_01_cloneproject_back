package com.cloneproject.ssgjojo.review.service;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.ReviewDeleteDto;
import com.cloneproject.ssgjojo.review.dto.ReviewDto;
import com.cloneproject.ssgjojo.review.dto.ReviewEditDto;
import com.cloneproject.ssgjojo.review.dto.ReviewOutputDto;
import com.cloneproject.ssgjojo.review.repository.IReviewRepository;
import com.cloneproject.ssgjojo.reviewphoto.domain.ReviewPhoto;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewServiceImple implements IReviewService {

    private final IReviewRepository iReviewRepository;
    private final IProductRepository iProductRepository;
    private final IUserRepository iUserRepository;

    @Override
    public Review addReview(ReviewDto reviewDto) {

        Optional<User> user = iUserRepository.findById(reviewDto.getUserId());
        Optional<Product> product = iProductRepository.findById(reviewDto.getProductId());

        if(user.isPresent() && product.isPresent()) {
            return iReviewRepository.save(Review.builder()
                    .title(reviewDto.getTitle())
                    .mainText(reviewDto.getMainText())
                    .score(reviewDto.getScore())
                    .user(user.get())
                    .product(product.get())
                    .build());
        }

        return null;
    }

    @Override
    public ReviewEditDto editReview(ReviewEditDto reviewEditDto) {

        Optional<User> user = iUserRepository.findById(reviewEditDto.getUserId());
        Optional<Product> product = iProductRepository.findById(reviewEditDto.getProductId());
        Optional<Review> review = iReviewRepository.findById(reviewEditDto.getId());

        if (review.isPresent() && user.isPresent() && product.isPresent()) {
            if(review.get().getUser().getId() == reviewEditDto.getUserId()) {
                Review reviewEdit = iReviewRepository.save(Review.builder()
                        .id(reviewEditDto.getId())
                        .title(reviewEditDto.getTitle())
                        .mainText(reviewEditDto.getMainText())
                        .score(reviewEditDto.getScore())
                        .user(user.get())
                        .product(product.get())
                        .build());

                return ReviewEditDto.builder()
                        .id(review.get().getId())
                        .title(reviewEdit.getTitle())
                        .mainText(reviewEdit.getMainText())
                        .score(reviewEdit.getScore())
                        .userId(reviewEdit.getUser().getId())
                        .productId(reviewEdit.getProduct().getId())
                        .build();
            }
        }

        return null;
    }

    @Override
    public List<ReviewOutputDto> getReviewByProductId(Long id) {
        Optional<Product> product = iProductRepository.findById(id);
        List<ReviewOutputDto> reviewOutputDtoList = new ArrayList<>();

        if(product.isPresent()) {
            List<Review> reviewList = iReviewRepository.findAllByProduct(product.get());

            if (!reviewList.isEmpty()) {
                for (Review review : reviewList) {
                    reviewOutputDtoList.add(ReviewOutputDto.builder()
                            .id(review.getId())
                            .title(review.getTitle())
                            .mainText(review.getMainText())
                            .score(review.getScore())
                            .userId(review.getUser().getUserId())
                            .productId(review.getProduct().getId())
                            .createdTime(review.getCreatedDate())
                            .build());
                }
            }

            return reviewOutputDtoList;
        }

        return null;
    }

    @Override
    public List<ReviewOutputDto> sortedGetReviewByProductId(Long id, int sort) {
        Optional<Product> product = iProductRepository.findById(id);
        List<ReviewOutputDto> returnDto = new ArrayList<>();
        List<Review> temp = new ArrayList<>();

        if(product.isPresent()) {
            if(sort == 1)
                temp = iReviewRepository.findAllByProductOrderByScoreAsc(product.get());
            else if(sort == 2)
                temp = iReviewRepository.findAllByProductOrderByScoreDesc(product.get());
            else if(sort == 3)
                temp = iReviewRepository.findAllByProductOrderByCreatedDateDesc(product.get());
            else
                temp = iReviewRepository.findAllByProduct(product.get());

            for(Review review : temp) {
                returnDto.add(ReviewOutputDto.builder()
                        .id(review.getId())
                        .title(review.getTitle())
                        .mainText(review.getMainText())
                        .score(review.getScore())
                        .userId(review.getUser().getUserId())
                        .productId(review.getProduct().getId())
                        .createdTime(review.getCreatedDate())
                        .build());
            }

            return returnDto;
        }

        return null;
    }

    @Override
    public List<Review> getAllReview() {

        return iReviewRepository.findAll();
    }

    @Override
    public List<ReviewOutputDto> getTop5(Long productId) {
        Optional<Product> product = iProductRepository.findById(productId);
        List<ReviewOutputDto> returnDtoList = new ArrayList<>();

        if(product.isPresent()) {
            List<Review> reviewList = iReviewRepository.findTop5ByProduct(product.get());

            for(Review review : reviewList) {
                returnDtoList.add(ReviewOutputDto.builder()
                        .id(review.getId())
                        .title(review.getTitle())
                        .mainText(review.getMainText())
                        .score(review.getScore())
                        .userId(review.getUser().getUserId())
                        .productId(review.getProduct().getId())
                        .createdTime(review.getCreatedDate())
                        .build());
            }

            return returnDtoList;
        }
        return null;
    }

    @Override
    public Float getReviewAvgScore(Long productId) {

        Float avgScore = iReviewRepository.getReviewAvgScore(productId);
        return (float) (Math.round(avgScore*10)/10.0);
    }

    @Override
    public List<ReviewOutputDto> findAllByUser(Long userId) {

        Optional<User> user = iUserRepository.findById(userId);
        List<ReviewOutputDto> returnDtoList = new ArrayList<>();

        if(user.isPresent()) {
            List<Review> reviewList = iReviewRepository.findAllByUser(user.get());

            for(Review review : reviewList) {
                returnDtoList.add(ReviewOutputDto.builder()
                        .id(review.getId())
                        .userId(review.getUser().getUserId())
                        .productId(review.getProduct().getId())
                        .title(review.getTitle())
                        .mainText(review.getMainText())
                        .score(review.getScore())
                        .createdTime(new Timestamp(System.currentTimeMillis()))
                        .build());
            }

            return returnDtoList;
        }

        return null;
    }

    // 접근지시자(public) 반환형(Integer) 함수의이름(getReviewCountByProduct) 전달받을변수의이름(자료형 변수이름)
    @Override
    public Integer getReviewCountByProduct(Long productId) {

        Integer review = iReviewRepository.getReviewCountByProduct(productId);
        return review;
    }

    @Override
    public void deleteReview(ReviewDeleteDto reviewDeleteDto) {

        Optional<User> user = iUserRepository.findById(reviewDeleteDto.getUserId());
        Optional<Review> review = iReviewRepository.findById(reviewDeleteDto.getId());

        if (user.isPresent() && review.isPresent()) {
            if(review.get().getUser().getId() == reviewDeleteDto.getUserId()) {
                iReviewRepository.deleteById(reviewDeleteDto.getId());
            }
        }
    }


}
