package com.cloneproject.ssgjojo.producttimedeal.service;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.producttimedeal.domain.ProductTimeDeal;
import com.cloneproject.ssgjojo.producttimedeal.dto.ProductTimeDealAddDto;
import com.cloneproject.ssgjojo.producttimedeal.repository.IProductTimeDealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductTimeDealServiceImple implements IProductTimeDealService{
    private final IProductTimeDealRepository iProductTimeDealRepository;
    private final IProductRepository iProductRepository;

    @Override
    public ProductTimeDeal addTimeDeal(ProductTimeDealAddDto productTimeDealAddDto) {
        Optional<Product> product = iProductRepository.findById(productTimeDealAddDto.getProductId());

        return iProductTimeDealRepository.save(ProductTimeDeal.builder()
                        .timeDealName(productTimeDealAddDto.getTimeDealName())
                        .timeDealStartDate(productTimeDealAddDto.getTimeDealStartDate())
                        .timeDealEndDate(productTimeDealAddDto.getTimeDealEndDate())
                        .price(productTimeDealAddDto.getPrice())
                        .product(product.get())
                .build());
    }

    @Override
    public List<ProductTimeDeal> getAll() {
        return null;
    }

    @Override
    public ProductTimeDeal findOneTimeDeal() {
        return null;
    }
}
