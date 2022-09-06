package com.cloneproject.ssgjojo.coupon.controller;

import com.cloneproject.ssgjojo.coupon.domain.Coupon;
import com.cloneproject.ssgjojo.coupon.dto.CouponAddDto;
import com.cloneproject.ssgjojo.coupon.dto.CouponUseGetIdDto;
import com.cloneproject.ssgjojo.coupon.service.ICouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CouponController {

    private final ICouponService iCouponService;

    @PostMapping("/coupon/add") // 쿠폰 추가
    public ResponseEntity<?> addCoupon(@RequestBody CouponAddDto couponAddDto, HttpServletRequest request) {
        Coupon coupon = iCouponService.addCoupon(couponAddDto, request);

        if(coupon!=null){
            return ResponseEntity.status(200).body("쿠폰이 추가되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @GetMapping("/coupon/get") // 해당 사용자의 쿠폰 조회
    public ResponseEntity<?> getCoupon(HttpServletRequest request) {
        List<CouponUseGetIdDto> coupon = iCouponService.getCouponByUserId(request);

        if(coupon!=null){
            return ResponseEntity.status(200).body(coupon);
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @PutMapping("/coupon/delete/{id}") // 해당 사용자의 쿠폰 삭제
    public ResponseEntity<?> deleteCoupon(@PathVariable Long id) {

        Coupon coupon = iCouponService.deleteCoupon(id);

        if(coupon!=null){
            return ResponseEntity.status(200).body("쿠폰이 삭제되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }
}
