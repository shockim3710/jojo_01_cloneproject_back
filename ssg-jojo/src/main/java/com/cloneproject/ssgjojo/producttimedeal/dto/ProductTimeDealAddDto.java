package com.cloneproject.ssgjojo.producttimedeal.dto;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProductTimeDealAddDto {

    private String timeDealName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp timeDealStartDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp timeDealEndDate;
    private Long price;

    private Long productId;
}
