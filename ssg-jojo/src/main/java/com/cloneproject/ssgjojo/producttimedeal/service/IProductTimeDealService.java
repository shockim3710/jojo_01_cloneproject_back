package com.cloneproject.ssgjojo.producttimedeal.service;

import com.cloneproject.ssgjojo.producttimedeal.domain.ProductTimeDeal;
import com.cloneproject.ssgjojo.producttimedeal.dto.ProductTimeDealAddDto;

import java.util.List;

public interface IProductTimeDealService {
    ProductTimeDeal addTimeDeal(ProductTimeDealAddDto productTimeDealAddDto);
    List<ProductTimeDeal> getAll();

    ProductTimeDeal findOneTimeDeal();
}
