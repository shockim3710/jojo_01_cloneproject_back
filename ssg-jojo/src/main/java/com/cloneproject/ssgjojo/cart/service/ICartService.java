package com.cloneproject.ssgjojo.cart.service;

import com.cloneproject.ssgjojo.cart.dto.CartAddDto;
import com.cloneproject.ssgjojo.cart.dto.CartEditGetIdDto;
import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.orders.dto.OrdersAddDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersEditGetAllDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersGetIdDto;

import java.util.List;

public interface ICartService {

    CartAddDto addCart(CartAddDto cartAddDto);
    List<CartEditGetIdDto> getCartByUserId(Long id);
//    CartEditGetIdDto editCart(CartEditGetIdDto cartEditGetIdDto);

    void deleteCart(Long id);
    void deleteCartAll(Long id);


}
