package com.example.ssgmall.product.controller;

import com.example.ssgmall.product.domain.Product;
import com.example.ssgmall.product.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService iProductService;

    @PostMapping("/product/add")
    public Product addProduct(@RequestBody Product product) {
        return iProductService.addProduct(product);
    }

    @GetMapping("/product/getAll")
    public List<Product> getAll() { return iProductService.getAll(); }
}
