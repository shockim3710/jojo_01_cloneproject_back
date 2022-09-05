package com.cloneproject.ssgjojo.review.dto;

import com.cloneproject.ssgjojo.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private String title;
    private String mainText;
    private int score;
    private Long productId;
    private Long ordersId;

}
