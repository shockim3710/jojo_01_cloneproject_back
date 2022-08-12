package com.cloneproject.ssgjojo.deliveryAddress.service;

import com.cloneproject.ssgjojo.deliveryAddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.deliveryAddress.dto.DeliveryAddressAddDto;
import com.cloneproject.ssgjojo.deliveryAddress.dto.DeliveryAddressEditGetIdDto;
import com.cloneproject.ssgjojo.deliveryAddress.repository.IDeliveryAddressRepository;
import com.cloneproject.ssgjojo.recentSearches.domain.RecentSearches;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryAddressServiceImple implements IDeliveryAddressService {

    private final IDeliveryAddressRepository iDeliveryAddressRepository;
    private final IUserRepository iUserRepository;

    @Override
    public DeliveryAddressAddDto addDeliveryAddress(DeliveryAddressAddDto deliveryAddressAddDto) { // 배송지 추가
        Optional<User> user = iUserRepository.findById(deliveryAddressAddDto.getUser());

        if (user.isPresent()) {

            DeliveryAddress temp = iDeliveryAddressRepository.save(DeliveryAddress.builder()
                            .address(deliveryAddressAddDto.getAddress())
                            .whetherDefaultAddress(deliveryAddressAddDto.isWhetherDefaultAddress())
                            .user(user.get())
                            .build());

            return DeliveryAddressAddDto.builder()
                    .address(temp.getAddress())
                    .whetherDefaultAddress(temp.isWhetherDefaultAddress())
                    .user(temp.getUser().getId())
                    .build();
        }

        return null;
    }

    @Override
    public List<DeliveryAddressEditGetIdDto> getDeliveryAddressByUserId(Long id) { // 해당 사용자의 배송지 조회
        Optional<User> userOptional = iUserRepository.findById(id);

        if(userOptional.isPresent()) {
            List<DeliveryAddress> deliveryAddressList = iDeliveryAddressRepository.findAllByUser(userOptional.get());
            List<DeliveryAddressEditGetIdDto> deliveryAddressEditGetIdDtoList = new ArrayList<>();

            deliveryAddressList.forEach(user -> {
                deliveryAddressEditGetIdDtoList.add(DeliveryAddressEditGetIdDto.builder()
                                .id(user.getId())
                                .address(user.getAddress())
                                .whetherDefaultAddress(user.isWhetherDefaultAddress())
                                .user(user.getUser().getId())
                                .build());

            });

            return deliveryAddressEditGetIdDtoList;
        }

        return null;
    }

    @Override
    public DeliveryAddressEditGetIdDto editDeliveryAddress(DeliveryAddressEditGetIdDto deliveryAddressEditGetIdDto) { // 배송지 수정
        Optional<DeliveryAddress> deliveryAddress = iDeliveryAddressRepository.findById(deliveryAddressEditGetIdDto.getId());
        Optional<User> user = iUserRepository.findById(deliveryAddressEditGetIdDto.getId());

        if(deliveryAddress.isPresent() && user.isPresent()) {
            DeliveryAddress temp = iDeliveryAddressRepository.save(DeliveryAddress.builder()
                            .id(deliveryAddressEditGetIdDto.getId())
                            .address(deliveryAddressEditGetIdDto.getAddress())
                            .whetherDefaultAddress(deliveryAddressEditGetIdDto.isWhetherDefaultAddress())
                            .user(iUserRepository.findById(deliveryAddressEditGetIdDto.getUser()).get())
                            .build());

            return DeliveryAddressEditGetIdDto.builder()
                    .id(temp.getId())
                    .address(temp.getAddress())
                    .whetherDefaultAddress(temp.isWhetherDefaultAddress())
                    .user(temp.getUser().getId())
                    .build();
        }

        return null;
    }

    @Override
    public void deleteDeliveryAddress(Long id) { // 해당 사용자의 배송지 삭제
        Optional<DeliveryAddress> deliveryAddress = iDeliveryAddressRepository.findById(id);

        if(deliveryAddress.isPresent()) {
            iDeliveryAddressRepository.deleteById(id);
        }

    }
}
