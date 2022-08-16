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
public class OrdersGetIdDto {
    private Long id;
    private int count; // 하나의 상품을 몇개 주문하는지
    private Timestamp orderDate; // 주문 날짜
    private Long orderPrice; // 주문한 총 가격
    private boolean isRefund; // 환불여부
    private String orderName; // 주문자 이름
    private String orderPhone; // 주문자 전화번호
    private Timestamp deliveryDate; // 배송날짜
    private String deliveryRequest; // 배송요청사항

    private Long user;
    private Long product;
    private Long productOption;
}
