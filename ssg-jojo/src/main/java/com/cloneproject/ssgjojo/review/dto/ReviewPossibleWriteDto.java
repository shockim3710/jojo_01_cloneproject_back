package com.cloneproject.ssgjojo.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewPossibleWriteDto {

    private Long id;

    private Long useId;
    private Long ordersId;
    private Long productId;
}
