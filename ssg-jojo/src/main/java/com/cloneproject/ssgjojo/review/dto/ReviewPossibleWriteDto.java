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
public class ReviewPossibleWriteDto {

    private Long productId;
    private Long ordersId;
    private String productThumbnail;
    private Timestamp deliveryDate;

}
