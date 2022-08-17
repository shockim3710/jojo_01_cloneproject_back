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
public class OrdersEditGetAllDto {
    private Long id;
    private int count; // 하나의 상품을 몇개 주문하는지
    private Long ordersPrice; // 주문한 총 가격
    private boolean whetherExchange; // 주문자 이름, 주문자 전화번호, 주문자 이메일, 배송지 변경여부
    // 배송지는 deliveryAddress 테이블에서 수정, 추가
    private boolean whetherRefund; // 환불여부
    private String ordersName; // 주문자 이름 (주문 수정)
    private String ordersPhone; // 주문자 전화번호 (주문 수정)
    private String ordersEmail; // 주문자 이메일 (주문 수정)
    private Timestamp deliveryDate; // 배송날짜
    private String deliveryRequest; // 배송요청사항 (주문 수정)

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

    private Long productOption;
    private String productOption1Contents; // 옵션1
    private String productOption2Contents; // 옵션2
}
