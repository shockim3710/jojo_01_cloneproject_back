package com.cloneproject.ssgjojo.producttimedeal.service;

import com.cloneproject.ssgjojo.producttimedeal.domain.ProductTimeDeal;
import com.cloneproject.ssgjojo.producttimedeal.dto.ProductTimeDealAddDto;
import com.cloneproject.ssgjojo.producttimedeal.dto.ProductTimeDealOutputDto;

import java.util.List;

public interface IProductTimeDealService {
    ProductTimeDealOutputDto addTimeDeal(ProductTimeDealAddDto productTimeDealAddDto);

    List<ProductTimeDealOutputDto> findTimeDealList();
}
