package com.cloneproject.ssgjojo.orders.controller;

import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.orders.dto.OrdersAddDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersEditGetAllDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersGetIdDto;
import com.cloneproject.ssgjojo.orders.service.IOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrdersController {

    private final IOrdersService iOrdersService;

    @PostMapping("/orders/add")
    public OrdersAddDto addOrders(@RequestBody OrdersAddDto ordersAddDto, HttpServletRequest request) {
        return iOrdersService.addOrders(ordersAddDto, request);
    }

    @GetMapping("/orders/get")
    public List<OrdersGetIdDto> getOrders(HttpServletRequest request) {
        return iOrdersService.getOrdersByUserId(request);
    }

    @PutMapping("/orders/edit")
    public OrdersEditGetAllDto editOrders(@RequestBody OrdersEditGetAllDto ordersEditGetAllDto, HttpServletRequest request) {
        return iOrdersService.editOrders(ordersEditGetAllDto, request);
    }

    @GetMapping("/orders/get/getAll")
    public List<Orders> getAllOrders() {
        return iOrdersService.getAllOrders();
    }

    @DeleteMapping("/orders/delete/{id}")
    public void deleteOrders(@PathVariable Long id) {
        iOrdersService.deleteOrders(id);
    }

}
