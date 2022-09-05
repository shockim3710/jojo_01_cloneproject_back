package com.cloneproject.ssgjojo.cartproductlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartProductListAddDto {
    private int cartCount; // 하나의 상품을 몇개 주문하는지

    private Long product;
    private String productName; // 상품 이름
    private String manufactureCompany; // 상품 제조사
    private String thumbnail; // 썸네일
    private Long price; // 가격
    private int discountRate; // 할인율
    private int fee; // 배송비

    private Long productOption;
    private String productOption1Contents; // 옵션1
    // 옵션 구조 변경으로 인하여 DTO에서 제거
    // private String productOption2Contents; // 옵션2
    private int stock; // 재고

    private Long cart;
}

