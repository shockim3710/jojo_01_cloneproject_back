package com.cloneproject.ssgjojo.orders.dto;

import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListAddDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersAddDto {
    private Long ordersPrice; // 주문한 총 가격
    private boolean whetherExchange; // 주문자 이름, 주문자 전화번호, 주문자 이메일, 배송지 변경여부
    private String ordersName; // 주문자 이름
    private String ordersPhone; // 주문자 전화번호
    private String ordersEmail; // 주문자 이메일
    private Timestamp deliveryDate; // 배송날짜
    private String deliveryRequest; // 배송요청사항

    private Long user;
    private String name; // 사용자 이름
    private String phone; // 사용자 전화번호
    private String email; // 사용자 이메일

    private Long deliveryAddress;
    private String address; // 배송지
    private List<OrdersProductListAddDto> ordersProductListAddDtoList;

}
