package com.cloneproject.ssgjojo.cartproductlist.controller;

import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListAddDto;
import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListGetIdEditDto;
import com.cloneproject.ssgjojo.cartproductlist.service.ICartProductListService;
import com.cloneproject.ssgjojo.ordersproductlist.service.IOrdersProductListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CartProductListController {

    private final ICartProductListService iCartProductListService;



}
