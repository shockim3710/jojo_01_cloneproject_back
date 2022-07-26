package com.cloneproject.ssgjojo.productoption.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionOutputDto {
    private Long id;
    private String productOption1Name;
    private String productOption1Contents;

    private int stock;

    private Long productId;
}
