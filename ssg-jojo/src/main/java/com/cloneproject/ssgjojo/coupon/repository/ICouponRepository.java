package com.cloneproject.ssgjojo.coupon.repository;

import com.cloneproject.ssgjojo.coupon.domain.Coupon;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICouponRepository extends JpaRepository<Coupon, Long> {



}
