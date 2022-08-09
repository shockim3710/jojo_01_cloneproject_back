package com.cloneproject.ssgjojo.review.service;

import com.cloneproject.ssgjojo.categoryLv3.domain.CategoryLv3;
import com.cloneproject.ssgjojo.categoryLv4.domain.CategoryLv4;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.ReviewDto;
import com.cloneproject.ssgjojo.review.repository.IReviewRepository;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

        Optional<User> user = iUserRepository.findById(reviewDto.getId());
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
    public Review editReview(Review review) {
        return null;
    }

    @Override
    public Review getReviewById(Long id) {

        return null;
    }

    @Override
    public List<Review> getAllReview() {

        return null;
    }

    @Override
    public void deleteReview(Long id) {

    }
}
