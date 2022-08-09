package com.cloneproject.ssgjojo.product.service;


import com.cloneproject.ssgjojo.categoryLv4.domain.CategoryLv4;
import com.cloneproject.ssgjojo.categoryLv4.repository.ICategoryLv4Repository;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.dto.ProductAddDto;
import com.cloneproject.ssgjojo.product.dto.ProductUpdateDto;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImple implements IProductService {
    private final IProductRepository iProductRepository;
    private final ICategoryLv4Repository iCategoryLv4Repository;

    @Override
    public Product addProduct(ProductAddDto productAddDto) {
        Optional<CategoryLv4> categoryLv4 = iCategoryLv4Repository.findById(productAddDto.getCategoryLv4());

        if(categoryLv4.isPresent()) {
            return iProductRepository.save(Product.builder()
                    .productName(productAddDto.getProductName())
                    .price(productAddDto.getPrice())
                    .description(productAddDto.getDescription())
                    .manufactureCompany(productAddDto.getManufactureCompany())
                    .discountRate(productAddDto.getDiscountRate())
                    .fee(productAddDto.getFee())
                    .color(productAddDto.getColor())
                    .size(productAddDto.getSize())
                    .availableStock(productAddDto.getAvailableStock())
                    .categoryLv4(iCategoryLv4Repository.findById(productAddDto.getCategoryLv4()).get())
                    .build()
            );
        }

        return null;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = iProductRepository.findById(id);

        if(product.isPresent()) {
            return product.get();
        }
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return iProductRepository.findAll();
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> product = iProductRepository.findById(id);

        if(product.isPresent()) {
            iProductRepository.deleteById(id);
        }
    }

    @Override
    public Product editProduct(ProductUpdateDto productUpdateDto) {
        Optional<Product> product = iProductRepository.findById(productUpdateDto.getId());
        Optional<CategoryLv4> categoryLv4 = iCategoryLv4Repository.findById(productUpdateDto.getId());

        if(product.isPresent() && categoryLv4.isPresent()) {
            return iProductRepository.save(Product.builder()
                    .id(productUpdateDto.getId())
                    .productName(productUpdateDto.getProductName())
                    .price(productUpdateDto.getPrice())
                    .description(productUpdateDto.getDescription())
                    .manufactureCompany(productUpdateDto.getManufactureCompany())
                    .discountRate(productUpdateDto.getDiscountRate())
                    .fee(productUpdateDto.getFee())
                    .color(productUpdateDto.getColor())
                    .size(productUpdateDto.getSize())
                    .availableStock(productUpdateDto.getAvailableStock())
                    .categoryLv4(iCategoryLv4Repository.findById(productUpdateDto.getCategoryLv4()).get())
                    .build()
            );
        }

        return null;


        /*
        Product updateProduct = iProductRepository.findById(productUpdateDto.getId()).get();

        // 더티 체킹 상태 변경 체크에서 사용함.
        // 필드 너무 많으면 부담스러움.
        updateProduct.setProductName(productUpdateDto.getProductName());
        */
    }
}
