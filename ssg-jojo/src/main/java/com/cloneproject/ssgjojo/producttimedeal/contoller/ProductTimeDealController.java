package com.cloneproject.ssgjojo.producttimedeal.contoller;

import com.cloneproject.ssgjojo.producttimedeal.domain.ProductTimeDeal;
import com.cloneproject.ssgjojo.producttimedeal.dto.ProductTimeDealAddDto;
import com.cloneproject.ssgjojo.producttimedeal.service.IProductTimeDealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductTimeDealController {
    private final IProductTimeDealService iProductTimeDealService;

    @PostMapping("/timedeal/add")
    public ProductTimeDeal addProductTimeDeal(@RequestBody ProductTimeDealAddDto productTimeDealAddDto) {
        return iProductTimeDealService.addTimeDeal(productTimeDealAddDto);
    }

    @GetMapping("/timedeal/one")
    public ProductTimeDeal findOneTimeDeal() {

    }
}
