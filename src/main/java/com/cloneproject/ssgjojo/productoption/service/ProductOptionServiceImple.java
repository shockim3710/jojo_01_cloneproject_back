package com.cloneproject.ssgjojo.productoption.service;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.productoption.domain.ProductOption;
import com.cloneproject.ssgjojo.productoption.dto.ProductOptionAddDto;
import com.cloneproject.ssgjojo.productoption.dto.ProductOptionInfoDto;
import com.cloneproject.ssgjojo.productoption.dto.ProductOptionListDto;
import com.cloneproject.ssgjojo.productoption.dto.ProductOptionOutputDto;
import com.cloneproject.ssgjojo.productoption.repository.IProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ProductOptionServiceImple implements IProductOptionService{
    private final IProductOptionRepository iProductOptionRepository;
    private final IProductRepository iProductRepository;

    @Override
    public ProductOption addProductOption(ProductOptionAddDto productOptionAddDto) {
        Optional<Product> product = iProductRepository.findById(productOptionAddDto.getProductId());

        if(product.isPresent()) {
            return iProductOptionRepository.save(ProductOption.builder()
                            .productOption1Contents(productOptionAddDto.getProductOption1Contents())
                            .productOption1Name(productOptionAddDto.getProductOption1Name())
                            .stock(productOptionAddDto.getStock())
                            .product(product.get())
                    .build());
        }
        return null;
    }

    @Override
    public ProductOption getProductOptionById(Long id) {
        return iProductOptionRepository.findById(id).get();
    }

    @Override
    public ProductOptionListDto getProductOptionByProductId(Long id) {
        Optional<Product> product = iProductRepository.findById(id);

        if(product.isPresent()) {
            List<ProductOption> productOptionList = iProductOptionRepository.findAllByProduct(product.get());
            List<ProductOptionInfoDto> returnDto = new ArrayList<>();
            if(productOptionList.size() == 0) {
                return null;
            }

            int optionCnt = productOptionList.size();
            String optionName = productOptionList.get(0).getProductOption1Name();


            for(ProductOption option : productOptionList) {
                returnDto.add(ProductOptionInfoDto.builder()
                                .optionId(option.getId())
                                .productOption1Contents(option.getProductOption1Contents())
                                .stock(option.getStock())
                                .build());
            }

            return ProductOptionListDto.builder()
                    .productOption1Name(optionName)
                    .optionCnt(optionCnt)
                    .options(returnDto)
                    .build();
        }

        return null;
    }

    @Override
    public ProductOption editProductOption(ProductOption productOption) {
        Optional<Product> product = iProductRepository.findById(productOption.getProduct().getId());
        Optional<ProductOption> optionTemp = iProductOptionRepository.findById(productOption.getId());

        if(product.isPresent() && optionTemp.isPresent()) {
            if(optionTemp.get().getProduct().getId() == product.get().getId()) {
                return iProductOptionRepository.save(ProductOption.builder()
                                .id(productOption.getId())
                                .productOption1Name(productOption.getProductOption1Name())
                                .productOption1Contents(productOption.getProductOption1Contents())
                                .product(product.get())
                                .stock(productOption.getStock())
                        .build());
            }
        }
        return null;
    }

    @Override
    public void deleteProductOption(Long id) {
        Optional<ProductOption> productOption = iProductOptionRepository.findById(id);

        if(productOption.isPresent()) {
            iProductOptionRepository.deleteById(id);
        }

    }
}
