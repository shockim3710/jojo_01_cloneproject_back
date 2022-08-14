package com.cloneproject.ssgjojo.product.dto;

import com.cloneproject.ssgjojo.productdetailphoto.domain.ProductDetailPhoto;
import com.cloneproject.ssgjojo.productdetailphoto.dto.ProductDetailPhotoDto;
import com.cloneproject.ssgjojo.productoption.domain.ProductOption;
import com.cloneproject.ssgjojo.productoption.dto.ProductOptionOutDto;
import com.cloneproject.ssgjojo.productphoto.domain.ProductPhoto;
import com.cloneproject.ssgjojo.productphoto.dto.ProductPhotoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoDto {
    private Long id;
    private int price;
    private String description;
    private String productName;
    private String manufactureCompany;
    private int discountRate;
    private int fee;
    private boolean adultCase;
    private Long categoryLv4;
    private Long categoryLv3;
    private Long categoryLv2;
    private Long categoryLv1;
    private String thumbnail;

    private List<ProductOptionOutDto> productOptionList;
    private List<ProductPhotoDto> productPhotoList;
    private List<ProductDetailPhotoDto> productDetailPhotoList;
}
