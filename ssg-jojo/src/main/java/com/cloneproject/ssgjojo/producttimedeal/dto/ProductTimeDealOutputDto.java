package com.cloneproject.ssgjojo.producttimedeal.dto;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductTimeDealOutputDto {
    private Long id;
    private String timeDealName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp timeDealStartDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp timeDealEndDate;

    private String productThumbnail;
    private int timeDealPercent;

    private Long originPrice;
    private Long discountPrice;

    private Long productId;
}
