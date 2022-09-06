package com.cloneproject.ssgjojo.coupon.repository;

import com.cloneproject.ssgjojo.coupon.domain.Coupon;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICouponRepository extends JpaRepository<Coupon, Long> {

    List<Coupon> findAllByUser(User user);
}
