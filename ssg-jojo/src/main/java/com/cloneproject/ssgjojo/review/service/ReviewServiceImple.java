package com.cloneproject.ssgjojo.review.service;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.ReviewDeleteDto;
import com.cloneproject.ssgjojo.review.dto.ReviewDto;
import com.cloneproject.ssgjojo.review.dto.ReviewEditDto;
import com.cloneproject.ssgjojo.review.dto.ReviewOutputDto;
import com.cloneproject.ssgjojo.review.repository.IReviewRepository;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
                            .userId(review.getUser().getId())
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
    public List<Review> getAllReview() {

        return iReviewRepository.findAll();
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
