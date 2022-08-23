package com.cloneproject.ssgjojo.reviewphoto.repository;

import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.reviewphoto.domain.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReviewPhotoRepository extends JpaRepository<ReviewPhoto, Long> {



//    select * from review_photo rp
//    where rp.review_id = 24
    List<ReviewPhoto> findAllByReview(Review review);
}
