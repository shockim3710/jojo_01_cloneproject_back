package com.cloneproject.ssgjojo.product.controller;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.dto.*;
import com.cloneproject.ssgjojo.product.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/product/add", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addProductWithImage(@RequestParam("thumbnail") MultipartFile thumb,
                                       @RequestParam("productPhoto") List<MultipartFile> productPhoto,
                                       @RequestParam("productDetail") List<MultipartFile> productDetail,
                                       @RequestPart ProductAddDto productAddDto) {
        return iProductService.addProduct(productAddDto, thumb, productPhoto, productDetail);
    }

    @GetMapping("/product/detail/{productId}")
    public ProductDetailDto getProductDetail(@PathVariable Long productId, HttpServletRequest request) {
        return iProductService.getProductDetail(productId, request);
    }

    @GetMapping("/product/getlist")
    public List<ProductListAttentionDto> getProductAllList(HttpServletRequest request) {
        return iProductService.getAllProductList(request);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Long id) {
        iProductService.deleteProduct(id);
    }

    @PutMapping("/product")
    public Product editProduct(@RequestBody ProductUpdateDto productUpdateDto) {
        return iProductService.editProduct(productUpdateDto);
    }

    // 카테고리별 상품 조회
    @GetMapping("/product/findbycategory")
    public ProductInfoCategoryDto findByCategory(@RequestParam(name = "page", defaultValue = "1") int page,
                                                 @RequestParam(name = "lv", defaultValue = "4") Long lv,
                                                 @RequestParam(name = "id") Long id,
                                                 HttpServletRequest request) {
        return iProductService.findProductByCategoryLv(lv, id, page, request);

    }

    // 상품 검색
    @GetMapping("/product/search")
    public List<ProductListDto> searchProduct(@RequestParam String keyword, HttpServletRequest request) {
        return iProductService.productSearch(keyword, request);
    }
}
