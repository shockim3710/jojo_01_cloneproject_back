package com.cloneproject.ssgjojo.ordersproductlist.service;

import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListAddDto;
import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListGetIdEditDto;

import java.util.List;

public interface IOrdersProductListService {

    OrdersProductListAddDto addOrdersProductList(OrdersProductListAddDto ordersProductListAddDto);
    List<OrdersProductListGetIdEditDto> getOrdersProductListByOrdersId(Long id);
    void deleteOrdersProductList(Long id);
}
