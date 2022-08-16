package com.cloneproject.ssgjojo.orders.service;

import com.cloneproject.ssgjojo.orders.dto.OrdersAddDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersEditGetAllDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersGetIdDto;

import java.util.List;

public interface IOrdersService {

    OrdersAddDto addOrders(OrdersAddDto ordersAddDto);
    List<OrdersGetIdDto> getOrdersByUserId(Long id);
    OrdersEditGetAllDto editOrders(OrdersEditGetAllDto ordersEditGetAllDto);
    void deleteDeliveryAddress(Long id);

}
