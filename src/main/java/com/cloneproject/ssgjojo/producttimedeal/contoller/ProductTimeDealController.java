package com.cloneproject.ssgjojo.producttimedeal.contoller;

import com.cloneproject.ssgjojo.producttimedeal.domain.ProductTimeDeal;
import com.cloneproject.ssgjojo.producttimedeal.dto.ProductTimeDealAddDto;
import com.cloneproject.ssgjojo.producttimedeal.dto.ProductTimeDealOutputDto;
import com.cloneproject.ssgjojo.producttimedeal.service.IProductTimeDealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductTimeDealController {
    private final IProductTimeDealService iProductTimeDealService;

    // 타임 딜 추가
    @PostMapping("/timedeal/add")
    public ResponseEntity<?> addProductTimeDeal(@RequestBody ProductTimeDealAddDto productTimeDealAddDto) {
        ProductTimeDealOutputDto result = iProductTimeDealService.addTimeDeal(productTimeDealAddDto);

        if(result != null)
            return ResponseEntity.status(200).body(result);
        else
            return ResponseEntity.status(400).body("error page");
    }

    // 타임 딜 상품 조회
    @GetMapping("/timedeal/list")
    public ResponseEntity<?> findTimeDealList() {
        return ResponseEntity.status(200).body(iProductTimeDealService.findTimeDealList());
    }
}
