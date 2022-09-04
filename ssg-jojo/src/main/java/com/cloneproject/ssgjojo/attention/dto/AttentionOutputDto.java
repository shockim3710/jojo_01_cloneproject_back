package com.cloneproject.ssgjojo.attention.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AttentionOutputDto: 좋아요 항목 출력할 때 사용되는 Dto
 * productId: 상품 PK
 * productInfo: 상품 정보 (제조사 + 상품이름)
 * productPrice: 상품 가격
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttentionOutputDto {
    private Long productId;
    private String productInfo;
    private Long productPrice;
}
