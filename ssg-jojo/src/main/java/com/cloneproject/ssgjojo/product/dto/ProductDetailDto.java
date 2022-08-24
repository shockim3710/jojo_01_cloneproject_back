package com.cloneproject.ssgjojo.product.dto;

import com.cloneproject.ssgjojo.productdetailphoto.dto.ProductDetailPhotoDto;
import com.cloneproject.ssgjojo.productphoto.dto.ProductPhotoDto;
import com.cloneproject.ssgjojo.qna.dto.QnAOutputDto;
import com.cloneproject.ssgjojo.review.dto.ReviewOutputDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDto {

    private Long id;
    private String mallName;
    private String productName;
    private String manufactureCompany;
    private Long oldPrice;
    private Long newPrice;
    private int discountRate;
    private float reviewScore;
    private int reviewNum;

    private List<ProductPhotoDto> productPhotoList;
    private List<ProductDetailPhotoDto> productDetailPhotoList;
    private List<ReviewOutputDto> reviewList;
    private List<QnAOutputDto> QnaList;
}
