package com.cloneproject.ssgjojo.deliveryAddress.dto;

import com.cloneproject.ssgjojo.user.domain.User;

public class DeliveryAddressEditGetIdDto {
    private Long id;
    private String address; // 배송지 (배송지 수정)
    private boolean isDefaultDeliveryAddress; // 기본배송지 여부 (배송지 수정)

    private Long user;
}
