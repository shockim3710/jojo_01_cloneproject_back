package com.cloneproject.ssgjojo.productoption.dto;

import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionListDto {
    private String productOption1Name;
    private int optionCnt;
    List<ProductOptionInfoDto> options;
}
