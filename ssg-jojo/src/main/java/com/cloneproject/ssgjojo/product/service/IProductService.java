package com.cloneproject.ssgjojo.product.service;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.dto.ProductAddDto;
import com.cloneproject.ssgjojo.product.dto.ProductUpdateDto;
import com.cloneproject.ssgjojo.product.dto.ProductInfoDto;

import java.util.List;

public interface IProductService {
    Product addProduct(ProductAddDto ProductAddDto);
    ProductInfoDto getProductById(Long id);
    List<ProductInfoDto> getAllProduct();
    void deleteProduct(Long id);
    Product editProduct(ProductUpdateDto productUpdateDto);

}
