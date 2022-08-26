package com.cloneproject.ssgjojo.review.dto;

import com.cloneproject.ssgjojo.productphoto.dto.ProductPhotoDto;
import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.reviewphoto.domain.ReviewPhoto;
import com.cloneproject.ssgjojo.reviewphoto.dto.ReviewPhotoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewOutputDto {
    private Long id;
    private String title;
    private String mainText;
    private int score;
    private String userId;
    private Long productId;
    private Timestamp createdTime;

    List<ReviewPhotoDto> reviewPhotoDtoList;

    String jpql = "select rev from Review rev order by rev.createdTime desc";
    private EntityManager em;
    List<ReviewDto> reviewDtoList = em.createQuery("select rev from Review rev order by rev.createdTime desc", ReviewDto.class)
            .setFirstResult(1)
            .setMaxResults(10)
            .getResultList();
}
