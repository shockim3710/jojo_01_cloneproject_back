package com.cloneproject.ssgjojo.product.dto;

import com.cloneproject.ssgjojo.categorylv1.dto.CategoryDto;
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
    Long totalCnt;
    CategoryDto parentCategory;
    List<CategoryDto> sameLevelCategory;
    List<CategoryDto> childLevelCategory;
    List<ProductListDto> productList;
}
