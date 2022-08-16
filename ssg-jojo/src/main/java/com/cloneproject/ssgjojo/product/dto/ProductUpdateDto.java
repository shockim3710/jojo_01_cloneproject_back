package com.cloneproject.ssgjojo.product.dto;

import com.cloneproject.ssgjojo.productoption.domain.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDto {
    private Long id;
    private String productName;
    private int price;
    private String description;
    private String manufactureCompany;
    private int discountRate;
    private int fee;
    private boolean adultCase;
    private String thumbnail;

    private Long categoryLv1;
    private Long categoryLv2;
    private Long categoryLv3;
    private Long categoryLv4;

    private List<ProductOption> productOptionList;

}
