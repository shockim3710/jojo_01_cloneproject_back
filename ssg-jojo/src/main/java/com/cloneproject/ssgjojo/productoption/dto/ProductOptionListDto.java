package com.cloneproject.ssgjojo.productoption.dto;

import java.util.HashMap;

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
    HashMap<Integer, ProductOptionInfoDto> options;
}
