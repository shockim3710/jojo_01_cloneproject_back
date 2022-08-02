package com.studybusan.mvc.study.cart.service;

import com.studybusan.mvc.study.cart.dto.CartDtoInput;
import com.studybusan.mvc.study.cart.dto.CartDtoOutput;
import com.studybusan.mvc.study.cart.model.Cart;

import java.util.List;

public interface ICartService {

    Cart addCart(CartDtoInput cartDtoInput);
    List<CartDtoOutput> getAll();
    CartDtoOutput getCartById(Long id);
}
