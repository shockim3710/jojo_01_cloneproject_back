package com.busan.ssg.cart.controller;

import com.busan.ssg.cart.domain.Cart;
import com.busan.ssg.cart.dto.CartDto;
import com.busan.ssg.cart.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CartController {

    private final ICartService iCartService;

    @PostMapping("/cart/add")
    public Cart addCart(@RequestBody CartDto cartDto) {
        return iCartService.addCart(cartDto);
    }

    @GetMapping("/cart/getAll")
    public List<Cart> getAllCart() {
        return iCartService.getAllCart();
    }

    @GetMapping("/cart/getAllByUserId/{id}")
    public List<Cart> getAllByUserId(@PathVariable Long id) {
        return iCartService.getAllCartByUserId(id);
    }

}