package com.cloneproject.ssgjojo.coupon.dto;

import java.sql.Timestamp;

public class CouponAddDto {
    private String couponName; // 쿠폰 이름
    private Timestamp couponStartDate; // 쿠폰 시작날짜
    private Timestamp couponEndDate; // 쿠폰 마감날짜
    private String couponContent; // 쿠폰 내용
    private boolean isUseStatus; // 쿠폰 사용여부

    private Long user;
}
