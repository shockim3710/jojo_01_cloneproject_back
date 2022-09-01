package com.cloneproject.ssgjojo.orders.service;

import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.orders.dto.OrdersAddDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersEditGetAllDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersGetIdDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IOrdersService {

    OrdersAddDto addOrders(OrdersAddDto ordersAddDto, HttpServletRequest request);
    List<OrdersGetIdDto> getOrdersByUserId(HttpServletRequest request);
    OrdersEditGetAllDto editOrders(OrdersEditGetAllDto ordersEditGetAllDto, HttpServletRequest request);
    void deleteOrders(Long id);
    List<Orders> getAllOrders();


}
