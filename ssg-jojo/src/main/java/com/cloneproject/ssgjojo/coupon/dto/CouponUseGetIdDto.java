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
    private boolean whetherIsUseStatus; // 쿠폰 사용여부 (사용후 수정)
    private Long bargainPrice; // 쿠폰 할인가격
    private Long bargainPercent; // 쿠폰 할인퍼센트
    private Long useOverPrice; // 쿠폰 사용의 기본가격 (얼마 이상 구매시 사용가능)
    private Long maxUsePrice; // 최대 할인가격 (최대 얼마까지만 할인)

    private Long user;
}
