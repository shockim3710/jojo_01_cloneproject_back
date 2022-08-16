package com.cloneproject.ssgjojo.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersAddDto {
    private int count; // 하나의 상품을 몇개 주문하는지
    private Long orderPrice; // 주문한 총 가격
    private boolean whetherExchange; // 주문자 이름, 주문자 전화번호, 주문자 이메일, 배송지 변경여부
    private boolean whetherRefund; // 환불여부
    private String orderName; // 주문자 이름
    private String orderPhone; // 주문자 전화번호
    private String ordersEmail; // 주문자 이메일
    private Timestamp deliveryDate; // 배송날짜
    private String deliveryRequest; // 배송요청사항

    private Long user;
    private String name; // 사용자 이름
    private String phone; // 사용자 전화번호
    private String email; // 사용자 이메일

    private Long product;
    private String productName; // 상품 이름
    private String manufactureCompany; // 상품 제조사
//    private String thumbnail; // 썸네일

    private Long deliveryAddress;
    private String address; // 배송지
//    (private boolean whetherDefaultAddress; // 기본배송지 여부 == true)

    private Long productOption;
    private String productOption1Contents; // 옵션1
    private String productOption2Contents; // 옵션2
}
