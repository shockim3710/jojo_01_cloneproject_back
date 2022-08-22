package com.cloneproject.ssgjojo.cart.controller;

import com.cloneproject.ssgjojo.cart.domain.Cart;
import com.cloneproject.ssgjojo.cart.dto.CartAddDto;
import com.cloneproject.ssgjojo.cart.dto.CartEditGetIdDto;
import com.cloneproject.ssgjojo.cart.service.ICartService;
import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.orders.dto.OrdersAddDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersEditGetAllDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersGetIdDto;
import com.cloneproject.ssgjojo.orders.service.IOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CartController {

    private final ICartService iCartService;

    @PostMapping("/cart/add")
    public CartAddDto addCart(@RequestBody CartAddDto cartAddDto) {
        return iCartService.addCart(cartAddDto);
    }

    @GetMapping("/cart/get/{id}")
    public List<CartEditGetIdDto> getCart(@PathVariable Long id) {
        return iCartService.getCartByUserId(id);
    }

    @PutMapping("/cart/edit")
    public CartEditGetIdDto editCart(@RequestBody CartEditGetIdDto cartEditGetIdDto) {
        return iCartService.editCart(cartEditGetIdDto);
    }

    @DeleteMapping("/cart/delete/{id}")
    public void deleteCart(@PathVariable Long id) {
        iCartService.deleteCart(id);
    }

}
