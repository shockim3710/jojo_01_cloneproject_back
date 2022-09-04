package com.cloneproject.ssgjojo.deliveryaddress.controller;

import com.cloneproject.ssgjojo.deliveryaddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.deliveryaddress.dto.DeliveryAddressAddDto;
import com.cloneproject.ssgjojo.deliveryaddress.dto.DeliveryAddressEditGetIdDto;
import com.cloneproject.ssgjojo.deliveryaddress.service.IDeliveryAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DeliveryAddressController {

    private final IDeliveryAddressService iDeliveryAddressService;

    @PostMapping("/deliveryaddress/add") // 배송지 추가
    public ResponseEntity<?> addDeliveryAddress(@RequestBody DeliveryAddressAddDto deliveryAddressAddDto, HttpServletRequest request) {
        DeliveryAddress deliveryAddress = iDeliveryAddressService.addDeliveryAddress(deliveryAddressAddDto, request);

        if(deliveryAddress!=null){
            return ResponseEntity.status(200).body("배송지가 추가되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @GetMapping("/deliveryaddress/get") // 해당 사용자의 배송지 조회
    public ResponseEntity<?> getDeliveryAddress(HttpServletRequest request) {
        List<DeliveryAddressEditGetIdDto> deliveryAddress = iDeliveryAddressService.getDeliveryAddressByUserId(request);

        if(deliveryAddress!=null){
            return ResponseEntity.status(200).body(deliveryAddress);
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @PutMapping("/deliveryaddress/edit") // 배송지 수정
    public ResponseEntity<?> editDeliveryAddress(@RequestBody DeliveryAddressEditGetIdDto deliveryAddressEditGetIdDto, HttpServletRequest request) {
        DeliveryAddress deliveryAddress = iDeliveryAddressService.editDeliveryAddress(deliveryAddressEditGetIdDto, request);

        if(deliveryAddress!=null){
            return ResponseEntity.status(200).body("배송지가 수정되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @DeleteMapping("/deliveryaddress/delete/{id}") // 배송지 삭제
    public ResponseEntity<?> deleteDeliveryAddress(@PathVariable Long id) {
        Optional<DeliveryAddress> deliveryAddress = iDeliveryAddressService.deleteDeliveryAddress(id);

        if(deliveryAddress.isPresent()){
            return ResponseEntity.status(200).body("배송지가 삭제되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

}
