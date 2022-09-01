package com.cloneproject.ssgjojo.cart.controller;

import com.cloneproject.ssgjojo.cart.domain.Cart;
import com.cloneproject.ssgjojo.cart.dto.CartAddDto;
import com.cloneproject.ssgjojo.cart.dto.CartEditGetIdDto;
import com.cloneproject.ssgjojo.cart.service.ICartService;
import com.cloneproject.ssgjojo.cartproductlist.domain.CartProductList;
import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListAddDto;
import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListGetIdEditDto;
import com.cloneproject.ssgjojo.coupon.domain.Coupon;
import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.orders.dto.OrdersAddDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersEditGetAllDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersGetIdDto;
import com.cloneproject.ssgjojo.orders.service.IOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartController {

    private final ICartService iCartService;

    @PostMapping("/cart/add")
    public List<CartProductListAddDto> addCart(@RequestBody CartAddDto cartAddDto, HttpServletRequest request) {
        return iCartService.addCart(cartAddDto, request);
    }

    @GetMapping("/cart/get")
    public List<CartProductListGetIdEditDto> getCart(HttpServletRequest request) {
        return iCartService.getCartByUserId(request);
    }

    @PutMapping("/cart/edit")
    public String editCart(@RequestBody CartProductListGetIdEditDto cartProductListGetIdEditDto) {
        return iCartService.editCart(cartProductListGetIdEditDto);
    }

//    @PutMapping("/cart/edit")
//    public CartEditGetIdDto editCart(@RequestBody CartEditGetIdDto cartEditGetIdDto) {
//        return iCartService.editCart(cartEditGetIdDto);
//    }

    @DeleteMapping("/cart/delete/{id}")
    public void deleteCart(@PathVariable Long id) {
        iCartService.deleteCart(id);
    }

    @DeleteMapping("/cart/deleteall/{id}")
    public void deleteCartAll(@PathVariable Long id) {
        iCartService.deleteCartAll(id);
    }

}
