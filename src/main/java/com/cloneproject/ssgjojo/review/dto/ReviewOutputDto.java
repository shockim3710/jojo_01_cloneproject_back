package com.cloneproject.ssgjojo.review.dto;

import com.cloneproject.ssgjojo.reviewphoto.dto.ReviewPhotoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String userAccount;
    private Long productId;
    private Timestamp createdTime;

    List<ReviewPhotoDto> reviewPhotoDtoList;


}
