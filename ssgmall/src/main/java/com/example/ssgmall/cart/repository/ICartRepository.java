package com.example.ssgmall.cart.repository;

import com.example.ssgmall.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(Long id);
}
