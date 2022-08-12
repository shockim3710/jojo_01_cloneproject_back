package com.cloneproject.ssgjojo.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponUseGetIdDto {
    private Long id;
    private String couponName; // 쿠폰 이름
    private Timestamp couponStartDate; // 쿠폰 시작날짜
    private Timestamp couponEndDate; // 쿠폰 마감날짜
    private String couponContent; // 쿠폰 내용
    private boolean isUseStatus; // 쿠폰 사용여부 (사용후 수정)

    private Long user;
}
