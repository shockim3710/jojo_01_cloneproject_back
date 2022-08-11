package com.cloneproject.ssgjojo.productoption.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionAddDto {
    private String option1Name;
    private String option1Contents;
    private String option2Name;
    private String option2Contents;

    private int stock;

    private Long productId;
}
