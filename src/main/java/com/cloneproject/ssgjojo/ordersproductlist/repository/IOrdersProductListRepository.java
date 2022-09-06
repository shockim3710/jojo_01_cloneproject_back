package com.cloneproject.ssgjojo.ordersproductlist.repository;

import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.ordersproductlist.domain.OrdersProductList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrdersProductListRepository extends JpaRepository<OrdersProductList, Long> {

    List<OrdersProductList> findAllByOrders(Orders orders);
}
