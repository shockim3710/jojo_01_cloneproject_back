package com.cloneproject.ssgjojo.ordersproductlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersProductListGetIdEditDto {
    private Long id;
    private int count; // 하나의 상품을 몇개 주문하는지
    private boolean whetherRefund; // 환불여부

    private Long product;
    private String productName; // 상품 이름
    private String manufactureCompany; // 상품 제조사
    private String thumbnail; // 썸네일

    private Long productOption;
    private String productOption1Contents; // 옵션1
    private String productOption2Contents; // 옵션2

    private Long orders;
}
