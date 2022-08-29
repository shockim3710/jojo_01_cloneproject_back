package com.cloneproject.ssgjojo.productoption.controller;

import com.cloneproject.ssgjojo.productoption.domain.ProductOption;
import com.cloneproject.ssgjojo.productoption.dto.ProductOptionAddDto;
import com.cloneproject.ssgjojo.productoption.service.IProductOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductOptionController {
    private final IProductOptionService iProductOptionService;

    @PostMapping("/productoption/add")
    public ProductOption addProductOption(@RequestBody ProductOptionAddDto productOptionAddDto) {
        return iProductOptionService.addProductOption(productOptionAddDto);
    }

    @GetMapping("/productoption/{id}")
    public ProductOption getProductOption(@PathVariable Long id) {
        return iProductOptionService.getProductOptionById(id);
    }

    @GetMapping("/productoptionbyproduct/{id}")
    public List<ProductOption> getProductOptionByProduct(@PathVariable Long id) {
        return iProductOptionService.getProductOptionByProductId(id);
    }
}
