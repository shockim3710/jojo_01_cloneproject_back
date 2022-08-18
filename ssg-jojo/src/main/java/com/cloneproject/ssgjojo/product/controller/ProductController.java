package com.cloneproject.ssgjojo.product.controller;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.dto.ProductAddDto;
import com.cloneproject.ssgjojo.product.dto.ProductUpdateDto;
import com.cloneproject.ssgjojo.product.dto.ProductInfoDto;
import com.cloneproject.ssgjojo.product.service.IProductService;
import com.cloneproject.ssgjojo.productoption.domain.ProductOption;
import com.cloneproject.ssgjojo.productoption.dto.ProductOptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProductController {
    private final IProductService iProductService;

    @PostMapping("/product/add")
    public Product addProduct(@RequestBody ProductAddDto productAddDto) {
        return iProductService.addProduct(productAddDto);
    }

    @PostMapping(value = "/product/addImage", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addProductWithImage(@RequestParam("thumbnail") MultipartFile thumb,
                                       @RequestParam("productPhoto") List<MultipartFile> productPhoto,
                                       @RequestParam("productDetail") List<MultipartFile> productDetail,
                                       @RequestPart ProductAddDto productAddDto) {
        return iProductService.addProductWithPhoto(productAddDto, thumb, productPhoto, productDetail);
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
