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
    private Long ordersPrice; // 주문한 총 가격
    private String ordersName; // 주문자 이름
    private String ordersPhone; // 주문자 전화번호
    private String ordersEmail; // 주문자 이메일
    private Timestamp deliveryDate; // 배송날짜
    private String deliveryRequest; // 배송요청사항

    private Long user;

    private Long deliveryAddress;
    private String address; // 배송지
}
