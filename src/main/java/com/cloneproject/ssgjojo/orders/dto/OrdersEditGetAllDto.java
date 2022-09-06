package com.cloneproject.ssgjojo.orders.dto;

import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListGetIdEditDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersEditGetAllDto {
    private Long id;
    private Long ordersPrice; // 주문한 총 가격
    private boolean whetherExchange; // 주문자 이름, 주문자 전화번호, 주문자 이메일, 배송지 변경여부
    // 배송지는 deliveryAddress 테이블에서 수정, 추가
    private String ordersName; // 주문자 이름 (주문 수정)
    private String ordersPhone; // 주문자 전화번호 (주문 수정)
    private String ordersEmail; // 주문자 이메일 (주문 수정)
    private Date deliveryDate; // 배송날짜
    private String deliveryRequest; // 배송요청사항 (주문 수정)

    private Long user;

    private Long deliveryAddress;
    private String address; // 배송지
    private String addressName; // 주소별칭
    private String receiveName; // 받는분
    private String zipCode; // 우편번호
    private List<OrdersProductListGetIdEditDto> ordersProductListGetIdDtoListEdit;
}
