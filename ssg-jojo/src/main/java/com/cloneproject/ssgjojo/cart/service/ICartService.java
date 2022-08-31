package com.cloneproject.ssgjojo.cart.service;

import com.cloneproject.ssgjojo.cart.dto.CartAddDto;
import com.cloneproject.ssgjojo.cart.dto.CartEditGetIdDto;
import com.cloneproject.ssgjojo.cartproductlist.domain.CartProductList;
import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListGetIdEditDto;
import com.cloneproject.ssgjojo.coupon.domain.Coupon;
import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.orders.dto.OrdersAddDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersEditGetAllDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersGetIdDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICartService {

    CartAddDto addCart(CartAddDto cartAddDto, HttpServletRequest request);
    List<CartProductListGetIdEditDto> getCartByUserId(HttpServletRequest request);
//    CartEditGetIdDto editCart(CartEditGetIdDto cartEditGetIdDto);

    CartProductList editCart(CartProductListGetIdEditDto cartProductListGetIdEditDto);


    void deleteCart(Long id);
    void deleteCartAll(Long id);


}
