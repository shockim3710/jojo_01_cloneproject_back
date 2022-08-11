package com.cloneproject.ssgjojo.product.service;


import com.cloneproject.ssgjojo.categoryLv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categoryLv1.repository.ICategoryLv1Repository;
import com.cloneproject.ssgjojo.categoryLv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categoryLv2.repository.ICategoryLv2Repository;
import com.cloneproject.ssgjojo.categoryLv3.domain.CategoryLv3;
import com.cloneproject.ssgjojo.categoryLv3.repository.ICategoryLv3Repository;
import com.cloneproject.ssgjojo.categoryLv4.domain.CategoryLv4;
import com.cloneproject.ssgjojo.categoryLv4.repository.ICategoryLv4Repository;
import com.cloneproject.ssgjojo.categoryProductList.domain.CategoryProductList;
import com.cloneproject.ssgjojo.categoryProductList.repository.ICategoryProductListRepository;
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
    private final ICategoryLv3Repository iCategoryLv3Repository;
    private final ICategoryLv2Repository iCategoryLv2Repository;
    private final ICategoryLv1Repository iCategoryLv1Repository;

    private final ICategoryProductListRepository iCategoryProductListRepository;

    @Override
    public Product addProduct(ProductAddDto productAddDto) {
        Optional<CategoryLv4> categoryLv4 = iCategoryLv4Repository.findById(productAddDto.getCategoryLv4());
        Optional<CategoryLv3> categoryLv3 = iCategoryLv3Repository.findById(productAddDto.getCategoryLv3());
        Optional<CategoryLv2> categoryLv2 = iCategoryLv2Repository.findById(productAddDto.getCategoryLv2());
        Optional<CategoryLv1> categoryLv1 = iCategoryLv1Repository.findById(productAddDto.getCategoryLv1());


        if(categoryLv4.isPresent() && categoryLv3.isPresent() && categoryLv2.isPresent() && categoryLv1.isPresent()) {
            Product product = iProductRepository.save(Product.builder()
                    .productName(productAddDto.getProductName())
                    .price(productAddDto.getPrice())
                    .discountRate(productAddDto.getDiscountRate())
                    .description(productAddDto.getDescription())
                    .manufactureCompany(productAddDto.getManufactureCompany())
                    .fee(productAddDto.getFee())
                    .isAdultCase(productAddDto.isAdultCase())
                    .build()
            );

            iCategoryProductListRepository.save(CategoryProductList.builder()
                            .categoryLv1(categoryLv1.get())
                            .categoryLv2(categoryLv2.get())
                            .categoryLv3(categoryLv3.get())
                            .categoryLv4(categoryLv4.get())
                            .product(product)
                    .build());

            return product;
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
        Optional<CategoryLv4> categoryLv4 = iCategoryLv4Repository.findById(productUpdateDto.getCategoryLv4());
        Optional<CategoryLv3> categoryLv3 = iCategoryLv3Repository.findById(productUpdateDto.getCategoryLv3());
        Optional<CategoryLv2> categoryLv2 = iCategoryLv2Repository.findById(productUpdateDto.getCategoryLv2());
        Optional<CategoryLv1> categoryLv1 = iCategoryLv1Repository.findById(productUpdateDto.getCategoryLv1());

        if(product.isPresent() && categoryLv4.isPresent() && categoryLv3.isPresent() && categoryLv2.isPresent() && categoryLv1.isPresent()) {
            Product editProduct = iProductRepository.save(Product.builder()
                    .id(productUpdateDto.getId())
                    .productName(productUpdateDto.getProductName())
                    .price(productUpdateDto.getPrice())
                    .description(productUpdateDto.getDescription())
                    .manufactureCompany(productUpdateDto.getManufactureCompany())
                    .discountRate(productUpdateDto.getDiscountRate())
                    .fee(productUpdateDto.getFee())
                    .isAdultCase(productUpdateDto.isAdultCase())
                    .build()
            );

            iCategoryProductListRepository.save(CategoryProductList.builder()
                    .categoryLv1(categoryLv1.get())
                    .categoryLv2(categoryLv2.get())
                    .categoryLv3(categoryLv3.get())
                    .categoryLv4(categoryLv4.get())
                    .product(editProduct)
                    .build());

            return editProduct;
        }

        return null;

    }
}
