package com.cloneproject.ssgjojo.deliveryaddress.service;

import com.cloneproject.ssgjojo.deliveryaddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.deliveryaddress.dto.DeliveryAddressAddDto;
import com.cloneproject.ssgjojo.deliveryaddress.dto.DeliveryAddressEditGetIdDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface IDeliveryAddressService {

    DeliveryAddress addDeliveryAddress(DeliveryAddressAddDto deliveryAddressAddDto, HttpServletRequest request);
    List<DeliveryAddressEditGetIdDto> getDeliveryAddressByUserId(HttpServletRequest request);
    DeliveryAddress editDeliveryAddress(DeliveryAddressEditGetIdDto deliveryAddressEditGetIdDto, HttpServletRequest request);
    Optional<DeliveryAddress> deleteDeliveryAddress(Long id);
}
