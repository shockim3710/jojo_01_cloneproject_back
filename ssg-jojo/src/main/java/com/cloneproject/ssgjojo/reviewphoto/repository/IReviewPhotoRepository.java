package com.cloneproject.ssgjojo.reviewphoto.repository;

import com.cloneproject.ssgjojo.reviewphoto.domain.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewPhotoRepository extends JpaRepository<ReviewPhoto, Long> {
}
