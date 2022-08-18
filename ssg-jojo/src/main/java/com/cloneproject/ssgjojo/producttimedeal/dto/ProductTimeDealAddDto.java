package com.cloneproject.ssgjojo.producttimedeal.dto;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTimeDealAddDto {

    private String timeDealName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp timeDealStartDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp timeDealEndDate;
    private int timeDealPercent;

    private Long productId;
}
