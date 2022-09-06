package com.cloneproject.ssgjojo.reviewphoto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewPhotoDto {

    private Long id;
    private String reviewPhotoPath;

    private Long reviewId;
}
