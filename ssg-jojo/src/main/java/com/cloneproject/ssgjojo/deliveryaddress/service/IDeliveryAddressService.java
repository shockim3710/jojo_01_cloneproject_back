package com.cloneproject.ssgjojo.deliveryaddress.service;

import com.cloneproject.ssgjojo.deliveryaddress.dto.DeliveryAddressAddDto;
import com.cloneproject.ssgjojo.deliveryaddress.dto.DeliveryAddressEditGetIdDto;

import java.util.List;

public interface IDeliveryAddressService {

    DeliveryAddressAddDto addDeliveryAddress(DeliveryAddressAddDto deliveryAddressAddDto);
    List<DeliveryAddressEditGetIdDto> getDeliveryAddressByUserId(Long id);
    DeliveryAddressEditGetIdDto editDeliveryAddress(DeliveryAddressEditGetIdDto deliveryAddressEditGetIdDto);
    void deleteDeliveryAddress(Long id);
}
