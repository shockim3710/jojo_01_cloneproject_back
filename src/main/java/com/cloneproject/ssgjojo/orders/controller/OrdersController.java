package com.cloneproject.ssgjojo.orders.controller;

import com.cloneproject.ssgjojo.orders.dto.OrdersAddDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersEditGetAllDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersGetIdDto;
import com.cloneproject.ssgjojo.orders.service.IOrdersService;
import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListAddDto;
import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListGetIdEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrdersController {

    private final IOrdersService iOrdersService;

    @PostMapping("/orders/add") // 주문 추가
    public ResponseEntity<?> addOrders(@RequestBody OrdersAddDto ordersAddDto, HttpServletRequest request) {
        List<OrdersProductListAddDto> orders = iOrdersService.addOrders(ordersAddDto, request);

        if(orders!=null){
            return ResponseEntity.status(200).body("주문이 완료되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @GetMapping("/orders/get") // 해당 사용자의 주문 조회
    public ResponseEntity<?> getOrders(HttpServletRequest request) {
        List<OrdersGetIdDto> orders = iOrdersService.getOrdersByUserId(request);

        if(orders!=null){
            return ResponseEntity.status(200).body(orders);
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @PutMapping("/orders/edit") // 주문자 정보 수정
    public ResponseEntity<?> editOrders(@RequestBody OrdersEditGetAllDto ordersEditGetAllDto, HttpServletRequest request) {
        List<OrdersProductListGetIdEditDto> orders = iOrdersService.editOrders(ordersEditGetAllDto, request);

        if(orders!=null){
            return ResponseEntity.status(200).body("주문자 정보가 수정되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @DeleteMapping("/orders/delete/{id}") // 주문 삭제
    public ResponseEntity<?> deleteOrders(@PathVariable Long id) {
        boolean orders = iOrdersService.deleteOrders(id);

        if(orders == true){
            return ResponseEntity.status(200).body("주문이 삭제되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

}
