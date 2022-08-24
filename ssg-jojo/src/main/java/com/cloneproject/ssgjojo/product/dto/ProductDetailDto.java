package com.cloneproject.ssgjojo.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDto {

    private Long id;
    private Long productId;
    private Long mallName;
    private String productName;
    private Long price;
    private int discountRate;
    private int reviewCount;
    private float reviewAvg;


}
