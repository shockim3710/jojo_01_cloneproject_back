package com.cloneproject.ssgjojo.cartproductlist.controller;

import com.cloneproject.ssgjojo.cartproductlist.service.ICartProductListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartProductListController {

    private final ICartProductListService iCartProductListService;
}
