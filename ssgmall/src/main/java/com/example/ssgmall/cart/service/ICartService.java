package com.example.ssgmall.cart.service;

import com.example.ssgmall.cart.domain.Cart;
import com.example.ssgmall.cart.dto.CartDto;

import java.util.List;

public interface ICartService {
    Cart addCart(CartDto cartDto);
    List<Cart> getAllCart();
    List<Cart> getAllCartByUserId(Long id);
}
