package com.cloneproject.ssgjojo.productdetailphoto.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailPhotoDto {
    private String productDetailPhotoPath;
    private String productDetailPhotoOriginName;
    private int productDetailPhotoSeq;
    private Long productId;
}
