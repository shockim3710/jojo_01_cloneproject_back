package com.cloneproject.ssgjojo.producttimedeal.contoller;

import com.cloneproject.ssgjojo.producttimedeal.domain.ProductTimeDeal;
import com.cloneproject.ssgjojo.producttimedeal.dto.ProductTimeDealAddDto;
import com.cloneproject.ssgjojo.producttimedeal.dto.ProductTimeDealOutputDto;
import com.cloneproject.ssgjojo.producttimedeal.service.IProductTimeDealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductTimeDealController {
    private final IProductTimeDealService iProductTimeDealService;

    @PostMapping("/timedeal/add")
    public ProductTimeDealOutputDto addProductTimeDeal(@RequestBody ProductTimeDealAddDto productTimeDealAddDto) {
        return iProductTimeDealService.addTimeDeal(productTimeDealAddDto);
    }

    @GetMapping("/timedeal/list")
    public List<ProductTimeDealOutputDto> findTimeDealList() {
        return iProductTimeDealService.findTimeDealList();
    }

}
