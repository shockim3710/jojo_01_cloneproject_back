package com.cloneproject.ssgjojo.deliveryaddress.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddressAddDto {
    private String address; // 배송지
    private boolean whetherDefaultAddress; // 기본배송지 여부
    private boolean whetherOnlyThisTime; // 이번만배송지 여부
    private String addressName; // 주소별칭
    private String receiveName; // 받는분
    private String zipCode; // 우편번호

    private Long user;
}
