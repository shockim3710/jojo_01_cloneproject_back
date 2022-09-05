package com.cloneproject.ssgjojo.productoption.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionInfoDto {
    private Long optionId;
    private String productOption1Contents;
    private int stock;
}
