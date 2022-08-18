package com.cloneproject.ssgjojo.product.dto;

import com.cloneproject.ssgjojo.productoption.dto.ProductOptionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddDto {
    private String productName;
    private Long price;
    private String description;
    private String manufactureCompany;
    private int discountRate;
    private int fee;

    private boolean adultCase;


    private Long categoryLv4;
    private Long categoryLv3;
    private Long categoryLv2;
    private Long categoryLv1;

    private int stock;

    private List<ProductOptionDto> productOptionDtoList;
}
