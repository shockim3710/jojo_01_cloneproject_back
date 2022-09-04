package com.cloneproject.ssgjojo.producttimedeal.service;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.producttimedeal.domain.ProductTimeDeal;
import com.cloneproject.ssgjojo.producttimedeal.dto.ProductTimeDealAddDto;
import com.cloneproject.ssgjojo.producttimedeal.dto.ProductTimeDealOutputDto;
import com.cloneproject.ssgjojo.producttimedeal.repository.IProductTimeDealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductTimeDealServiceImple implements IProductTimeDealService{
    private final IProductTimeDealRepository iProductTimeDealRepository;
    private final IProductRepository iProductRepository;



    // 타임 딜 추가
    @Override
    public ProductTimeDealOutputDto addTimeDeal(ProductTimeDealAddDto productTimeDealAddDto) {
        Optional<Product> product = iProductRepository.findById(productTimeDealAddDto.getProductId());

        if(product.isPresent()) {
            ProductTimeDeal timeDeal = iProductTimeDealRepository.save(ProductTimeDeal.builder()
                    .timeDealName(productTimeDealAddDto.getTimeDealName())
                    .timeDealStartDate(productTimeDealAddDto.getTimeDealStartDate())
                    .timeDealEndDate(productTimeDealAddDto.getTimeDealEndDate())
                    .timeDealPercent(productTimeDealAddDto.getTimeDealPercent())
                    .product(product.get())
                    .build());

            Long discountPrice = (long) ((float) product.get().getPrice() * (1 - ((float) timeDeal.getTimeDealPercent() / 100)));

            return ProductTimeDealOutputDto.builder()
                    .id(timeDeal.getId())
                    .timeDealName(timeDeal.getTimeDealName())
                    .timeDealStartDate(timeDeal.getTimeDealStartDate())
                    .timeDealEndDate(timeDeal.getTimeDealEndDate())
                    .timeDealPercent(timeDeal.getTimeDealPercent())
                    .originPrice(product.get().getPrice())
                    .productThumbnail(product.get().getThumbnail())
                    .discountPrice(discountPrice)
                    .productId(product.get().getId())
                    .build();
        }

        return null;
    }


    // 타임 딜 상품 조회
    @Override
    public List<ProductTimeDealOutputDto> findTimeDealList() {
        List<ProductTimeDeal> timeDealList = iProductTimeDealRepository.findProductTimeDealList();
        List<ProductTimeDealOutputDto> returnDto = new ArrayList<>();
        if(!timeDealList.isEmpty()) {
            for (ProductTimeDeal timeDeal : timeDealList) {
                Optional<Product> product = iProductRepository.findById(timeDeal.getProduct().getId());

                if(!product.isPresent())
                    continue;

                Long discountPrice = (long) ((float) product.get().getPrice() * (1 - ((float) timeDeal.getTimeDealPercent() / 100)));

                returnDto.add(ProductTimeDealOutputDto.builder()
                        .id(timeDeal.getId())
                        .productId(product.get().getId())
                        .productThumbnail(product.get().getThumbnail())
                        .timeDealName(timeDeal.getTimeDealName())
                        .timeDealStartDate(timeDeal.getTimeDealStartDate())
                        .timeDealEndDate(timeDeal.getTimeDealEndDate())
                        .timeDealPercent(timeDeal.getTimeDealPercent())
                        .originPrice(product.get().getPrice())
                        .discountPrice(discountPrice)
                        .build());
            }

            return returnDto;
        }
        return null;
    }
}
