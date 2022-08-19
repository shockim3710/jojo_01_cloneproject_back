package com.cloneproject.ssgjojo.orders.repository;

import com.cloneproject.ssgjojo.deliveryaddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.ordersproductlist.service.OrdersProductListServiceImple;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByUser(User user);
//    List<Orders> findAllByDeliveryAddressDefault(String DeliveryAddressDefault);

//    @Query(value = "select d.address from DeliveryAddress d where d.whetherDefaultAddress = true")
//    String getAddressDefault(@Param("id") String DeliveryAddressDefault);

}
