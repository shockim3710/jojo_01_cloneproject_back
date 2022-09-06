package com.cloneproject.ssgjojo.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewEditDto {
    private Long id;
    private String title;
    private String mainText;
    private int score;

    private Long userId;
    private Long productId;
    private Long orderId;
}
