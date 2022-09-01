package com.cloneproject.ssgjojo.deliveryaddress.service;

import com.cloneproject.ssgjojo.deliveryaddress.dto.DeliveryAddressAddDto;
import com.cloneproject.ssgjojo.deliveryaddress.dto.DeliveryAddressEditGetIdDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IDeliveryAddressService {

    DeliveryAddressAddDto addDeliveryAddress(DeliveryAddressAddDto deliveryAddressAddDto, HttpServletRequest request);
    List<DeliveryAddressEditGetIdDto> getDeliveryAddressByUserId(HttpServletRequest request);
    DeliveryAddressEditGetIdDto editDeliveryAddress(DeliveryAddressEditGetIdDto deliveryAddressEditGetIdDto, HttpServletRequest request);
    void deleteDeliveryAddress(Long id);
}
