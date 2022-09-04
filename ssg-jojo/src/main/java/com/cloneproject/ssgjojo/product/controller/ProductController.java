package com.cloneproject.ssgjojo.product.controller;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.dto.*;
import com.cloneproject.ssgjojo.product.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProductController {
    private final IProductService iProductService;

    // 상품 추가
    @PostMapping(value = "/product/add", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addProductWithImage(@RequestParam("thumbnail") MultipartFile thumb,
                                                 @RequestParam("productPhoto") List<MultipartFile> productPhoto,
                                                 @RequestParam("productDetail") List<MultipartFile> productDetail,
                                                 @RequestPart ProductAddDto productAddDto) {
        Product result = iProductService.addProduct(productAddDto, thumb, productPhoto, productDetail);

        if(result != null)
            return ResponseEntity.status(200).body(result);
        else
            return ResponseEntity.status(400).body("error page");

    }

    // 카테고리별 상품 조회
    @GetMapping("/product/findbycategory")
    public ResponseEntity<?> findByCategory(@RequestParam(name = "page", defaultValue = "1") int page,
                                                 @RequestParam(name = "lv", defaultValue = "4") Long lv,
                                                 @RequestParam(name = "id") Long id,
                                                 HttpServletRequest request) {
        ProductInfoCategoryDto result = iProductService.findProductByCategoryLv(lv, id, page, request);

        if(result != null)
            return ResponseEntity.status(200).body(result);
        else
            return ResponseEntity.status(400).body("error page");
    }

    // 상품 검색
    @GetMapping("/product/search")
    public ResponseEntity<?> searchProduct(@RequestParam String keyword,
                                              @RequestParam(name = "page", defaultValue = "1") int page,
                                              HttpServletRequest request) {
        return ResponseEntity.status(200).body(iProductService.productSearch(keyword, page, request));
    }

    // 상품 상세
    @GetMapping("/product/detail/{productId}")
    public ResponseEntity<?> getProductDetail(@PathVariable Long productId, HttpServletRequest request) {
        ProductDetailDto result = iProductService.getProductDetail(productId, request);

        if(result != null)
            return ResponseEntity.status(200).body(result);
        else
            return ResponseEntity.status(400).body("error page");
    }

    // 상품 리스트 조회
    @GetMapping("/product/getlist")
    public ResponseEntity<?> getProductAllList(HttpServletRequest request) {
        return ResponseEntity.status(200).body(iProductService.getAllProductList(request));
    }

    // 상품 편집
    @PutMapping("/product")
    public ResponseEntity<?> editProduct(@RequestBody ProductUpdateDto productUpdateDto) {
        Product result = iProductService.editProduct(productUpdateDto);

        if(result != null)
            return ResponseEntity.status(200).body(result);
        else
            return ResponseEntity.status(400).body("error page");
    }

    // 상품 삭제
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        boolean result = iProductService.deleteProduct(id);

        if(result)
            return ResponseEntity.status(200).body("삭제 완료");
        else
            return ResponseEntity.status(400).body("error page");
    }

}
