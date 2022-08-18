package com.cloneproject.ssgjojo.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
}
