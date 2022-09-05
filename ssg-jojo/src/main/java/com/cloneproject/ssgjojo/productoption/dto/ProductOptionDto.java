package com.cloneproject.ssgjojo.productoption.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionDto {
    private String productOption1Name;
    private String productOption1Contents;

    private Integer stock;

}
