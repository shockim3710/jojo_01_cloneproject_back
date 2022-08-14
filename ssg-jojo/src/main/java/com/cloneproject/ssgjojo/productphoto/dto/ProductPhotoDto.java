package com.cloneproject.ssgjojo.productphoto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPhotoDto {
    private String productPhotoPath;
    private String productPhotoOriginName;
    private int productPhotoSeq;
    private Long productId;
}
