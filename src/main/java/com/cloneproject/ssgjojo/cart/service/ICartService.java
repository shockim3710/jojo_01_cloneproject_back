package com.cloneproject.ssgjojo.cart.service;

import com.cloneproject.ssgjojo.cart.dto.CartAddDto;
import com.cloneproject.ssgjojo.cartproductlist.domain.CartProductList;
import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListAddDto;
import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListGetIdEditDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface ICartService {

    List<CartProductListAddDto> addCart(CartAddDto cartAddDto, HttpServletRequest request);
    List<CartProductListGetIdEditDto> getCartByUserId(HttpServletRequest request);
    Optional<CartProductList> editCart(CartProductListGetIdEditDto cartProductListGetIdEditDto);
    Optional<CartProductList> deleteCart(Long id);
    boolean deleteCartAll(Long id);

//    CartEditGetIdDto editCart(CartEditGetIdDto cartEditGetIdDto);
}
