package com.busan.ssg.product.controller;

import com.busan.ssg.product.domain.Product;
import com.busan.ssg.product.service.IProductService;
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
    public List<Product> getAll() {
        return iProductService.getAll();
    }
}
