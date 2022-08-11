package com.cloneproject.ssgjojo.deliveryAddress.dto;

import com.cloneproject.ssgjojo.user.domain.User;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

public class DeliveryAddressAddDto {
    private String address; // 배송지
    private boolean isDefaultDeliveryAddress; // 기본배송지 여부

    private Long user;
}
