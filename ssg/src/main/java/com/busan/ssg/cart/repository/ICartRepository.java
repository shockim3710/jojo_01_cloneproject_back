package com.busan.ssg.cart.repository;

import com.busan.ssg.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(Long id);
}
