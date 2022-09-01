package com.cloneproject.ssgjojo.cart.repository;

import com.cloneproject.ssgjojo.cart.domain.Cart;
import com.cloneproject.ssgjojo.cartproductlist.domain.CartProductList;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByUser(User user);
    Optional<Cart> findByUserId(Long userId);
}
