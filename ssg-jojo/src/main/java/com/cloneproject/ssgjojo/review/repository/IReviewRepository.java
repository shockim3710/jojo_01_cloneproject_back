package com.cloneproject.ssgjojo.review.repository;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findAllByProduct(Product product);
}
