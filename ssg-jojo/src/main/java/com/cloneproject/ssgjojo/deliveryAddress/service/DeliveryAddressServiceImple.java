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

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryAddressServiceImple implements IDeliveryAddressService {

    private final IDeliveryAddressRepository iDeliveryAddressRepository;
    private final IUserRepository iUserRepository;

    @Override
    public DeliveryAddressAddDto addDeliveryAddress(DeliveryAddressAddDto deliveryAddressAddDto) {
        Optional<User> user = iUserRepository.findById(deliveryAddressAddDto.getUser());

        if (user.isPresent()) {

            deliveryAddressAddDto.setDefaultDeliveryAddress(false);

            DeliveryAddress temp = iDeliveryAddressRepository.save(DeliveryAddress.builder()
                            .address(deliveryAddressAddDto.getAddress())
//                            .isDefaultDeliveryAddress(deliveryAddressAddDto.getIsDefaultDeliveryAddress())
                            .user(user.get())
                            .build());

            return DeliveryAddressAddDto.builder()
                    .address(temp.getAddress())
//                    .isDefaultDeliveryAddress(temp.get)
                    .user(temp.getUser().getId())
                    .build();
        }

        return null;
    }

    @Override
    public List<DeliveryAddressEditGetIdDto> getDeliveryAddressByUserId(Long id) {
        Optional<User> user = iUserRepository.findById(id);

        return null;
    }

    @Override
    public DeliveryAddress editDeliveryAddress(DeliveryAddressEditGetIdDto deliveryAddressEditGetIdDto) {
        return null;
    }

    @Override
    public void deleteDeliveryAddress(Long id) {
        Optional<DeliveryAddress> deliveryAddress = iDeliveryAddressRepository.findById(id);

        if(deliveryAddress.isPresent()) {
            iDeliveryAddressRepository.deleteById(id);
        }

    }
}
