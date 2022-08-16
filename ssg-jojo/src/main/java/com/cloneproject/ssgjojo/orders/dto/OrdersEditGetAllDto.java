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
    private Long orderPrice; // 주문한 총 가격
    private boolean whetherExchange; // 주문자 이름, 주문자 전화번호, 주문자 이메일, 배송지 변경여부
    private boolean whetherRefund; // 환불여부
    private String orderName; // 주문자 이름 (주문 수정)
    private String orderPhone; // 주문자 전화번호 (주문 수정)
    private String ordersEmail; // 주문자 이메일 (주문 수정)
    private Timestamp deliveryDate; // 배송날짜
    private String deliveryRequest; // 배송요청사항 (주문 수정)

    private Long user;
    private Long product;
    private Long productOption;
}
