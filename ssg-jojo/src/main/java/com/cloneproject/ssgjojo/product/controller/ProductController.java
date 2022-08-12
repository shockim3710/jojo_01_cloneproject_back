package com.cloneproject.ssgjojo.product.controller;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.dto.ProductAddDto;
import com.cloneproject.ssgjojo.product.dto.ProductUpdateDto;
import com.cloneproject.ssgjojo.product.dto.ProductInfoDto;
import com.cloneproject.ssgjojo.product.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;

    @PostMapping("/product/add")
    public Product addProduct(@RequestBody ProductAddDto productAddDto) {
        return iProductService.addProduct(productAddDto);
    }

    @GetMapping("/product/getAll")
    public List<ProductInfoDto> getAllProduct() {
        return iProductService.getAllProduct();
    }

    @GetMapping("/product/{id}")
    public ProductInfoDto getProduct(@PathVariable Long id) {
        return iProductService.getProductById(id);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Long id) {
        iProductService.deleteProduct(id);
    }

    @PutMapping("/product")
    public Product editProduct(@RequestBody ProductUpdateDto productUpdateDto) {
        return iProductService.editProduct(productUpdateDto);
    }
}
