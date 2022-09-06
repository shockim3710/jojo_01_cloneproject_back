package com.cloneproject.ssgjojo.orders.repository;

import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByUser(User user);
}
