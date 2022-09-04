package com.cloneproject.ssgjojo.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoCategoryDto {
    List<?> sameLevelCategory;
    List<?> childLevelCategory;
    List<ProductListDto> productList;
}
