package com.busan.ssg.cart.service;

import com.busan.ssg.cart.domain.Cart;
import com.busan.ssg.cart.dto.CartDto;

import java.util.List;

public interface ICartService {

    Cart addCart(CartDto cartDto);
    List<Cart> getAllCart();
    List<Cart> getAllCartByUserId(Long id);
}
