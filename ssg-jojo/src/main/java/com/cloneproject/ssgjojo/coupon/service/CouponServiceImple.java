package com.cloneproject.ssgjojo.coupon.service;

import com.cloneproject.ssgjojo.coupon.domain.Coupon;
import com.cloneproject.ssgjojo.coupon.dto.CouponAddDto;
import com.cloneproject.ssgjojo.coupon.dto.CouponUseGetIdDto;
import com.cloneproject.ssgjojo.coupon.repository.ICouponRepository;
import com.cloneproject.ssgjojo.jwt.JwtTokenProvider;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponServiceImple implements ICouponService {

    private final ICouponRepository iCouponRepository;
    private final IUserRepository iUserRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Coupon addCoupon(CouponAddDto couponAddDto, HttpServletRequest request) { // 쿠폰 추가
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);
        if (user.isPresent()) {

            couponAddDto.setWhetherIsUseStatus(false);

            return iCouponRepository.save(Coupon.builder()
                            .couponName(couponAddDto.getCouponName())
                            .couponContent(couponAddDto.getCouponContent())
                            .couponStartDate(couponAddDto.getCouponStartDate())
                            .couponEndDate(couponAddDto.getCouponEndDate())
                            .whetherIsUseStatus(couponAddDto.isWhetherIsUseStatus())
                            .bargainPrice(couponAddDto.getBargainPrice())
                            .bargainPercent(couponAddDto.getBargainPercent())
                            .useOverPrice(couponAddDto.getUseOverPrice())
                            .maxUsePrice(couponAddDto.getMaxUsePrice())
                            .user(user.get())
                            .build());
        }

        return null;
    }

    @Override
    public List<CouponUseGetIdDto> getCouponByUserId(HttpServletRequest request) { // 해당 사용자의 쿠폰 조회
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> userOptional = iUserRepository.findById(userId);

        if(userOptional.isPresent()) {
            List<Coupon> couponList = iCouponRepository.findAllByUser(userOptional.get());
            List<CouponUseGetIdDto> couponUseGetIdDtoList = new ArrayList<>();

            couponList.forEach(user -> {
                couponUseGetIdDtoList.add(CouponUseGetIdDto.builder()
                                .id(user.getId())
                                .couponName(user.getCouponName())
                                .couponContent(user.getCouponContent())
                                .couponStartDate(user.getCouponStartDate())
                                .couponEndDate(user.getCouponEndDate())
                                .whetherIsUseStatus(user.isWhetherIsUseStatus())
                                .bargainPrice(user.getBargainPrice())
                                .bargainPercent(user.getBargainPercent())
                                .useOverPrice(user.getUseOverPrice())
                                .maxUsePrice(user.getMaxUsePrice())
                                .build());
            });

            return couponUseGetIdDtoList;
        }

        return null;
    }

    @Override
    @Transactional
    public Coupon deleteCoupon(Long id) { // 해당 사용자의 쿠폰 삭제
        Optional<Coupon> coupon = iCouponRepository.findById(id);

        if(coupon.isPresent()) {
            coupon.get().setWhetherIsUseStatus(true);
            return coupon.get();
        }

        return null;
    }
}
