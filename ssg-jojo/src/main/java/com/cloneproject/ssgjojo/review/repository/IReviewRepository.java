package com.cloneproject.ssgjojo.review.repository;

import com.cloneproject.ssgjojo.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewRepository extends JpaRepository<Review, Long> {
}
