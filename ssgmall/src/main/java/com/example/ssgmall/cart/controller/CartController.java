package com.example.ssgmall.cart.controller;

import com.example.ssgmall.cart.domain.Cart;
import com.example.ssgmall.cart.dto.CartDto;
import com.example.ssgmall.cart.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartController {

    private final ICartService iCartService;

    @PostMapping("/cart/add")
    public Cart addCart(@RequestBody CartDto cartDto) { return iCartService.addCart(cartDto); }

    @GetMapping("/cart/getAll")
    public List<Cart> getAllCart() { return iCartService.getAllCart(); }
}
