package com.cloneproject.ssgjojo.ordersproductlist.controller;

import com.cloneproject.ssgjojo.orders.dto.OrdersAddDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersEditGetAllDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersGetIdDto;
import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListAddDto;
import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListGetIdDto;
import com.cloneproject.ssgjojo.ordersproductlist.service.IOrdersProductListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrdersProductListController {

    private final IOrdersProductListService iOrdersProductListService;

    @PostMapping("/ordersproductlist/add")
    public OrdersProductListAddDto addOrdersProductList(@RequestBody OrdersProductListAddDto ordersProductListAddDto) {
        return iOrdersProductListService.addOrdersProductList(ordersProductListAddDto);
    }

    @GetMapping("/ordersproductlist/get/{id}")
    public List<OrdersProductListGetIdDto> getOrdersProductList(@PathVariable Long id) {
        return iOrdersProductListService.getOrdersProductListByOrdersId(id);
    }

    @DeleteMapping("/ordersproductlist/delete/{id}")
    public void deleteOrdersProductList(@PathVariable Long id) {
        iOrdersProductListService.deleteOrdersProductList(id);
    }

}
