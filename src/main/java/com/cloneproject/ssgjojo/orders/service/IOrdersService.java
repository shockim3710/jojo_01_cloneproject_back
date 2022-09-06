package com.cloneproject.ssgjojo.orders.service;

import com.cloneproject.ssgjojo.orders.dto.OrdersAddDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersEditGetAllDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersGetIdDto;
import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListAddDto;
import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListGetIdEditDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IOrdersService {

    List<OrdersProductListAddDto> addOrders(OrdersAddDto ordersAddDto, HttpServletRequest request);
    List<OrdersGetIdDto> getOrdersByUserId(HttpServletRequest request);
    List<OrdersProductListGetIdEditDto> editOrders(OrdersEditGetAllDto ordersEditGetAllDto, HttpServletRequest request);
    boolean deleteOrders(Long id);
}
