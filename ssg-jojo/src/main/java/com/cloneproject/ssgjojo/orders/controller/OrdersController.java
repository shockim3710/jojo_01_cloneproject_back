package com.cloneproject.ssgjojo.orders.controller;

import com.cloneproject.ssgjojo.deliveryaddress.dto.DeliveryAddressAddDto;
import com.cloneproject.ssgjojo.deliveryaddress.dto.DeliveryAddressEditGetIdDto;
import com.cloneproject.ssgjojo.deliveryaddress.service.IDeliveryAddressService;
import com.cloneproject.ssgjojo.orders.dto.OrdersAddDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersEditGetAllDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersGetIdDto;
import com.cloneproject.ssgjojo.orders.service.IOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrdersController {

    private final IOrdersService iOrdersService;

    @PostMapping("/orders/add")
    public OrdersAddDto addOrders(@RequestBody OrdersAddDto ordersAddDto) {
        return iOrdersService.addOrders(ordersAddDto);
    }

    @GetMapping("/orders/get/{id}")
    public List<OrdersGetIdDto> getOrders(@PathVariable Long id) {
        return iOrdersService.getOrdersByUserId(id);
    }

    @PutMapping("/orders/edit")
    public OrdersEditGetAllDto editOrders(@RequestBody OrdersEditGetAllDto ordersEditGetAllDto) {
        return iOrdersService.editOrders(ordersEditGetAllDto);
    }

    @DeleteMapping("/orders/delete/{id}")
    public void deleteOrders(@PathVariable Long id) {
        iOrdersService.deleteOrders(id);
    }

}
