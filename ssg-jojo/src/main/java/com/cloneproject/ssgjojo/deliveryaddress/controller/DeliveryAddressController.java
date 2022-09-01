package com.cloneproject.ssgjojo.deliveryaddress.controller;

import com.cloneproject.ssgjojo.deliveryaddress.dto.DeliveryAddressAddDto;
import com.cloneproject.ssgjojo.deliveryaddress.dto.DeliveryAddressEditGetIdDto;
import com.cloneproject.ssgjojo.deliveryaddress.service.IDeliveryAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DeliveryAddressController {

    private final IDeliveryAddressService iDeliveryAddressService;

    @PostMapping("/deliveryaddress/add") // 배송지 추가
    public DeliveryAddressAddDto addDeliveryAddress(@RequestBody DeliveryAddressAddDto deliveryAddressAddDto, HttpServletRequest request) {
        return iDeliveryAddressService.addDeliveryAddress(deliveryAddressAddDto, request);
    }


    @GetMapping("/deliveryaddress/get") // 해당 사용자의 배송지 조회
    public List<DeliveryAddressEditGetIdDto> getDeliveryAddress(HttpServletRequest request) {
        return iDeliveryAddressService.getDeliveryAddressByUserId(request);
    }

    @PutMapping("/deliveryaddress/edit") // 배송지 수정
    public DeliveryAddressEditGetIdDto editDeliveryAddress(@RequestBody DeliveryAddressEditGetIdDto deliveryAddressEditGetIdDto, HttpServletRequest request) {
        return iDeliveryAddressService.editDeliveryAddress(deliveryAddressEditGetIdDto, request);
    }

    @DeleteMapping("/deliveryaddress/delete/{id}") // 해당 사용자의 배송지 삭제
    public void deleteDeliveryAddress(@PathVariable Long id) {
        iDeliveryAddressService.deleteDeliveryAddress(id);
    }

}
