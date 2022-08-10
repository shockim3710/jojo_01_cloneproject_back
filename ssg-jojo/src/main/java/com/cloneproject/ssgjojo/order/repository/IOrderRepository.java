package com.cloneproject.ssgjojo.order.repository;

import com.cloneproject.ssgjojo.order.domain.Order;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {


}
