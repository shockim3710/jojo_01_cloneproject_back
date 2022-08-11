package com.cloneproject.ssgjojo.deliveryAddress.controller;

import com.cloneproject.ssgjojo.deliveryAddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.deliveryAddress.dto.DeliveryAddressAddDto;
import com.cloneproject.ssgjojo.deliveryAddress.dto.DeliveryAddressEditGetIdDto;
import com.cloneproject.ssgjojo.deliveryAddress.service.IDeliveryAddressService;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.dto.ProductUpdateDto;
import com.cloneproject.ssgjojo.recentSearches.dto.RecentSearchesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DeliveryAddressController {

    private final IDeliveryAddressService iDeliveryAddressService;

    @PostMapping("/deliveryaddress/add")
    public DeliveryAddressAddDto addDeliveryAddress(@RequestBody DeliveryAddressAddDto deliveryAddressAddDto) {
        return iDeliveryAddressService.addDeliveryAddress(deliveryAddressAddDto);
    }


    @GetMapping("/deliveryaddress/get/{id}")
    public List<DeliveryAddressEditGetIdDto> getDeliveryAddress(@PathVariable Long id) {
        return iDeliveryAddressService.getDeliveryAddressByUserId(id);
    }

    @PutMapping("/deliveryaddress/edit")
    public DeliveryAddress editDeliveryAddress(@RequestBody DeliveryAddressEditGetIdDto deliveryAddressEditGetIdDto) {
        return iDeliveryAddressService.editDeliveryAddress(deliveryAddressEditGetIdDto);
    }

    @DeleteMapping("/deliveryaddress/delete/{id}")
    public void deleteDeliveryAddress(@PathVariable Long id) {
        iDeliveryAddressService.deleteDeliveryAddress(id);
    }

}
