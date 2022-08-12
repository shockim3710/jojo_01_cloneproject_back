package com.cloneproject.ssgjojo.deliveryAddress.service;

import com.cloneproject.ssgjojo.deliveryAddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.deliveryAddress.dto.DeliveryAddressAddDto;
import com.cloneproject.ssgjojo.deliveryAddress.dto.DeliveryAddressEditGetIdDto;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.dto.ProductUpdateDto;
import com.cloneproject.ssgjojo.recentSearches.dto.RecentSearchesAddDto;
import com.cloneproject.ssgjojo.recentSearches.dto.RecentSearchesDto;

import java.util.List;

public interface IDeliveryAddressService {

    DeliveryAddressAddDto addDeliveryAddress(DeliveryAddressAddDto deliveryAddressAddDto);
    List<DeliveryAddressEditGetIdDto> getDeliveryAddressByUserId(Long id);
    DeliveryAddressEditGetIdDto editDeliveryAddress(DeliveryAddressEditGetIdDto deliveryAddressEditGetIdDto);
    void deleteDeliveryAddress(Long id);
}
