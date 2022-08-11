package com.cloneproject.ssgjojo.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddDto {
    private int price;
    private String description;
    private String productName;
    private String manufactureCompany;
    private int discountRate;
    private int fee;
    private boolean isAdultCase;
    private Long categoryLv4;
    private Long categoryLv3;
    private Long categoryLv2;
    private Long categoryLv1;
}
