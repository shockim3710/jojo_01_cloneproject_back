package com.cloneproject.ssgjojo.coupon.controller;

import com.cloneproject.ssgjojo.coupon.domain.Coupon;
import com.cloneproject.ssgjojo.coupon.dto.CouponAddDto;
import com.cloneproject.ssgjojo.coupon.dto.CouponUseGetIdDto;
import com.cloneproject.ssgjojo.coupon.service.ICouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CouponController {

    private final ICouponService iCouponService;

    @PostMapping("/coupon/add")
    public CouponAddDto addCoupon(@RequestBody CouponAddDto couponAddDto, HttpServletRequest request) {
        return iCouponService.addCoupon(couponAddDto, request);
    }

    @GetMapping("/coupon/get")
    public List<CouponUseGetIdDto> getCoupon(HttpServletRequest request) {
        return iCouponService.getCouponByUserId(request);
    }

    @PutMapping("/coupon/delete/{id}")
    public Coupon deleteCoupon(@PathVariable Long id) {
        return iCouponService.deleteCoupon(id);
    }




}
