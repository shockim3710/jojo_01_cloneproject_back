package com.cloneproject.ssgjojo.deliveryaddress.service;

import com.cloneproject.ssgjojo.deliveryaddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.deliveryaddress.dto.DeliveryAddressAddDto;
import com.cloneproject.ssgjojo.deliveryaddress.dto.DeliveryAddressEditGetIdDto;
import com.cloneproject.ssgjojo.deliveryaddress.repository.IDeliveryAddressRepository;
import com.cloneproject.ssgjojo.jwt.JwtTokenProvider;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryAddressServiceImple implements IDeliveryAddressService {

    private final IDeliveryAddressRepository iDeliveryAddressRepository;
    private final IUserRepository iUserRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public DeliveryAddress addDeliveryAddress(DeliveryAddressAddDto deliveryAddressAddDto, HttpServletRequest request) { // 배송지 추가
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);

        if (user.isPresent()) {

            return iDeliveryAddressRepository.save(DeliveryAddress.builder()
                    .address(deliveryAddressAddDto.getAddress())
                    .whetherDefaultAddress(deliveryAddressAddDto.isWhetherDefaultAddress())
                    .whetherOnlyThisTime(deliveryAddressAddDto.isWhetherOnlyThisTime())
                    .receiveName(deliveryAddressAddDto.getReceiveName())
                    .addressName(deliveryAddressAddDto.getAddressName())
                    .zipCode(deliveryAddressAddDto.getZipCode())
                    .user(user.get())
                    .build());
        }

        return null;
    }

    @Override
    public List<DeliveryAddressEditGetIdDto> getDeliveryAddressByUserId(HttpServletRequest request) { // 해당 사용자의 배송지 조회
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));

        Optional<User> userOptional = iUserRepository.findById(userId);

        if(userOptional.isPresent()) {
            List<DeliveryAddressEditGetIdDto> deliveryAddressEditGetIdDtoList = new ArrayList<>();

            List<DeliveryAddress> deliveryAddressList = iDeliveryAddressRepository.findAllByUserOrderByWhetherDefaultAddressDesc(userOptional.get());

            deliveryAddressList.forEach(user -> {
                deliveryAddressEditGetIdDtoList.add(DeliveryAddressEditGetIdDto.builder()
                        .id(user.getId())
                        .address(user.getAddress())
                        .whetherDefaultAddress(user.isWhetherDefaultAddress())
                        .whetherOnlyThisTime(user.isWhetherOnlyThisTime())
                        .addressName(user.getAddressName())
                        .receiveName(user.getReceiveName())
                        .zipCode(user.getZipCode())
                        .user(user.getUser().getId())
                        .build());

            });

            return deliveryAddressEditGetIdDtoList;
        }

        return null;
    }

    @Override
    public DeliveryAddress editDeliveryAddress(DeliveryAddressEditGetIdDto deliveryAddressEditGetIdDto, HttpServletRequest request) { // 배송지 수정
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));

        Optional<DeliveryAddress> deliveryAddress = iDeliveryAddressRepository.findById(deliveryAddressEditGetIdDto.getId());
        Optional<User> user = iUserRepository.findById(userId);

        Optional<DeliveryAddress> deliveryAddressWhetherDefaultAddress = iDeliveryAddressRepository.findByWhetherDefaultAddressAndUserId(true, userId);
        Optional<DeliveryAddress> deliveryAddressWhetherOnlyThisTime = iDeliveryAddressRepository.findByWhetherOnlyThisTimeAndUserId(true, userId);

        if(deliveryAddress.isPresent() && user.isPresent()) {


            if(deliveryAddressEditGetIdDto.isWhetherDefaultAddress() == true && deliveryAddressEditGetIdDto.isWhetherOnlyThisTime() == true) {

                if(deliveryAddress.get().isWhetherDefaultAddress() == false && deliveryAddressWhetherDefaultAddress.isPresent()) {
                    deliveryAddressWhetherDefaultAddress.get().setWhetherDefaultAddress(false);
                } else if(deliveryAddress.get().isWhetherOnlyThisTime() == false && deliveryAddressWhetherOnlyThisTime.isPresent()) {
                    deliveryAddressWhetherOnlyThisTime.get().setWhetherOnlyThisTime(false);
                }

            } else if(deliveryAddressEditGetIdDto.isWhetherDefaultAddress() == true) {
                if(deliveryAddressWhetherDefaultAddress.isPresent()) {
                    deliveryAddressWhetherDefaultAddress.get().setWhetherDefaultAddress(false);
                }

            } else if (deliveryAddressEditGetIdDto.isWhetherOnlyThisTime() == true) {
                if(deliveryAddressWhetherOnlyThisTime.isPresent()) {
                    deliveryAddressWhetherOnlyThisTime.get().setWhetherOnlyThisTime(false);
                }
            }

            return iDeliveryAddressRepository.save(DeliveryAddress.builder()
                    .id(deliveryAddressEditGetIdDto.getId())
                    .address(deliveryAddressEditGetIdDto.getAddress())
                    .whetherDefaultAddress(deliveryAddressEditGetIdDto.isWhetherDefaultAddress())
                    .whetherOnlyThisTime(deliveryAddressEditGetIdDto.isWhetherOnlyThisTime())
                    .addressName(deliveryAddressEditGetIdDto.getAddressName())
                    .receiveName(deliveryAddressEditGetIdDto.getReceiveName())
                    .zipCode(deliveryAddressEditGetIdDto.getZipCode())
                    .user(user.get())
                    .build());
        }

        return null;
    }

    @Override
    public Optional<DeliveryAddress> deleteDeliveryAddress(Long id) { // 배송지 삭제
        Optional<DeliveryAddress> deliveryAddress = iDeliveryAddressRepository.findById(id);

        if(deliveryAddress.isPresent()) {
            iDeliveryAddressRepository.deleteById(id);

            return deliveryAddress;
        }

        return null;
    }
}
