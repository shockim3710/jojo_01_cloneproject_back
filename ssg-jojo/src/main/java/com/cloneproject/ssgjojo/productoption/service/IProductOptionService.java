package com.cloneproject.ssgjojo.productoption.service;

import com.cloneproject.ssgjojo.productoption.domain.ProductOption;
import com.cloneproject.ssgjojo.productoption.dto.ProductOptionAddDto;

import java.util.List;

public interface IProductOptionService {
    ProductOption addProductOption(ProductOptionAddDto productOptionAddDto);
    ProductOption getProductOptionById(Long id);
    ProductOption editProductOption(ProductOption productOption);
    void deleteProductOption(Long id);
    public List<ProductOption> getProductOptionByProductId(Long id);

}
