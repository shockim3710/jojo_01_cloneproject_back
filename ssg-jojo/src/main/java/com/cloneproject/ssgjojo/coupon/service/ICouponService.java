package com.cloneproject.ssgjojo.coupon.service;

import com.cloneproject.ssgjojo.coupon.domain.Coupon;
import com.cloneproject.ssgjojo.coupon.dto.CouponAddDto;
import com.cloneproject.ssgjojo.coupon.dto.CouponUseGetIdDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICouponService {

    CouponAddDto addCoupon(CouponAddDto couponAddDto, HttpServletRequest request);
    List<CouponUseGetIdDto> getCouponByUserId(HttpServletRequest request);
    Coupon deleteCoupon(Long id);

}
