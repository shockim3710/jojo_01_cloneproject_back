package com.cloneproject.ssgjojo.coupon.service;

import com.cloneproject.ssgjojo.coupon.domain.Coupon;
import com.cloneproject.ssgjojo.coupon.dto.CouponAddDto;
import com.cloneproject.ssgjojo.coupon.dto.CouponUseGetIdDto;
import com.cloneproject.ssgjojo.coupon.repository.ICouponRepository;
import com.cloneproject.ssgjojo.exceptionoutput.CouponException;
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
    public CouponAddDto addCoupon(CouponAddDto couponAddDto, HttpServletRequest request) {
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = Optional.ofNullable(iUserRepository.findById(userId).orElseThrow(CouponException::new));
//        Optional<User> user = iUserRepository.findById(couponAddDto.getUser());
        if (user.isPresent()) {

            couponAddDto.setWhetherIsUseStatus(false);

            Coupon temp = iCouponRepository.save(Coupon.builder()
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

            return  CouponAddDto.builder()
                    .couponName(temp.getCouponName())
                    .couponContent(temp.getCouponContent())
                    .couponStartDate(temp.getCouponStartDate())
                    .couponEndDate(temp.getCouponEndDate())
                    .whetherIsUseStatus(temp.isWhetherIsUseStatus())
                    .bargainPrice(temp.getBargainPrice())
                    .bargainPercent(temp.getBargainPercent())
                    .useOverPrice(temp.getUseOverPrice())
                    .maxUsePrice(temp.getMaxUsePrice())
                    .user(temp.getUser().getId())
                    .build();
        }

        return null;
    }

    @Override
    public List<CouponUseGetIdDto> getCouponByUserId(HttpServletRequest request) {
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> userOptional = Optional.ofNullable(iUserRepository.findById(userId).orElseThrow(CouponException::new));

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
    public Coupon deleteCoupon(Long id) {
        Optional<Coupon> coupon = iCouponRepository.findById(id);

        if(coupon.isPresent()) {
            coupon.get().setWhetherIsUseStatus(true);
            return coupon.get();
        }

        return null;
    }
}
