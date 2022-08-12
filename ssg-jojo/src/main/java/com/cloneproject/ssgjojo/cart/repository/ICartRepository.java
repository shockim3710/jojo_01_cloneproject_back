package com.cloneproject.ssgjojo.cart.repository;

import com.cloneproject.ssgjojo.cart.domain.Cart;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart, Long> {


}
