package com.cloneproject.ssgjojo.coupon.service;

import com.cloneproject.ssgjojo.coupon.domain.Coupon;
import com.cloneproject.ssgjojo.coupon.dto.CouponAddDto;
import com.cloneproject.ssgjojo.coupon.dto.CouponUseGetIdDto;

import java.util.List;

public interface ICouponService {

    CouponAddDto addCoupon(CouponAddDto couponAddDto);
    List<CouponUseGetIdDto> getCouponByUserId(Long id);
    Coupon deleteCoupon(Long id);

}
