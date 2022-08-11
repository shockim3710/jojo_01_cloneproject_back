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
import com.cloneproject.ssgjojo.product.dto.ProductEditDto;
import com.cloneproject.ssgjojo.product.dto.ProductInfoDto;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.productoption.domain.ProductOption;
import com.cloneproject.ssgjojo.productoption.dto.ProductOptionDto;
import com.cloneproject.ssgjojo.productoption.repository.IProductOptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
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

    private final IProductOptionRepository iProductOptionRepository;
    
    // 상품 추가
    @Override
    @Transactional
    public Product addProduct(ProductAddDto productAddDto) {
        Optional<CategoryLv1> categoryLv1 = iCategoryLv1Repository.findById(productAddDto.getCategoryLv1());
        Optional<CategoryLv2> categoryLv2 = iCategoryLv2Repository.findById(productAddDto.getCategoryLv2());
        Optional<CategoryLv3> categoryLv3 = iCategoryLv3Repository.findById(productAddDto.getCategoryLv3());
        Optional<CategoryLv4> categoryLv4 = iCategoryLv4Repository.findById(productAddDto.getCategoryLv4());
        
        // 카테고리 유효성 검증
        if(categoryLv1.isPresent() && categoryLv2.isPresent() && categoryLv3.isPresent() && categoryLv4.isPresent()) {
            // 상품 저장
            Product product = iProductRepository.save(Product.builder()
                    .productName(productAddDto.getProductName())
                    .price(productAddDto.getPrice())
                    .discountRate(productAddDto.getDiscountRate())
                    .description(productAddDto.getDescription())
                    .manufactureCompany(productAddDto.getManufactureCompany())
                    .fee(productAddDto.getFee())
                    .adultCase(productAddDto.isAdultCase())
                    .build()
            );

            // 상품 - 카테고리 중칸테이블 저장
            iCategoryProductListRepository.save(CategoryProductList.builder()
                    .categoryLv1(categoryLv1.get())
                    .categoryLv2(categoryLv2.get())
                    .categoryLv3(categoryLv3.get())
                    .categoryLv4(categoryLv4.get())
                    .product(product)
                    .build());

            // 상품 옵션 저장
            // 상품 등록할 때 옵션을 여러가지 등록할 수 있으므로 foreach로 하나씩 저장
            for(ProductOptionDto productOptionDto : productAddDto.getProductOptionDtoList()) {
                iProductOptionRepository.save(ProductOption.builder()
                    .product(product)
                    .productOption1Name(productOptionDto.getOption1Name())
                    .productOption1Contents(productOptionDto.getOption1Contents())
                    .productOption2Name(productOptionDto.getOption2Name())
                    .productOption2Contents(productOptionDto.getOption2Contents())
                    .stock(productOptionDto.getStock())
                    .build());
            }

            return product;
        }

        return null;
    }

    // 상품 아이디를 통한 저장
    @Override
    public ProductInfoDto getProductById(Long id) {
        Optional<Product> product = iProductRepository.findById(id);

        // 상품 아이디 유효성 검증
        if(product.isPresent()) {
            Optional<CategoryProductList> categoryProductList = iCategoryProductListRepository.findByProduct(product.get());
            List<ProductOption> productOption = iProductOptionRepository.findAllByProduct(product.get());

            
            // 중간 테이블 및 옵션 리스트 유효성 검증
            if(categoryProductList.isPresent() && !productOption.isEmpty()) {
                ProductInfoDto returnDto = ProductInfoDto.builder()
                        .id(product.get().getId())
                        .price(product.get().getPrice())
                        .description(product.get().getDescription())
                        .productName(product.get().getProductName())
                        .manufactureCompany(product.get().getManufactureCompany())
                        .discountRate(product.get().getDiscountRate())
                        .fee(product.get().getFee())
                        .adultCase(product.get().isAdultCase())
                        .categoryLv4(categoryProductList.get().getCategoryLv4().getId())
                        .categoryLv3(categoryProductList.get().getCategoryLv3().getId())
                        .categoryLv2(categoryProductList.get().getCategoryLv2().getId())
                        .categoryLv1(categoryProductList.get().getCategoryLv1().getId())
                        .productOptionList(productOption)
                        .build();

                return returnDto;
            }
        }
        return null;
    }

    @Override
    public List<ProductInfoDto> getAllProduct() {
        List<Product> productList = iProductRepository.findAll();
        List<ProductInfoDto> productInfoDtoList = new ArrayList<>();

        for(Product product : productList) {
            Optional<CategoryProductList> categoryProductList = iCategoryProductListRepository.findByProduct(product);
            List<ProductOption> productOptionList = iProductOptionRepository.findAllByProduct(product);


            // 중간 테이블 및 옵션 리스트 유효성 검증
            if(categoryProductList.isPresent() && !productOptionList.isEmpty()) {
                productInfoDtoList.add(ProductInfoDto.builder()
                        .id(product.getId())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .productName(product.getProductName())
                        .manufactureCompany(product.getManufactureCompany())
                        .discountRate(product.getDiscountRate())
                        .fee(product.getFee())
                        .adultCase(product.isAdultCase())
                        .categoryLv4(categoryProductList.get().getCategoryLv4().getId())
                        .categoryLv3(categoryProductList.get().getCategoryLv3().getId())
                        .categoryLv2(categoryProductList.get().getCategoryLv2().getId())
                        .categoryLv1(categoryProductList.get().getCategoryLv1().getId())
                        .productOptionList(productOptionList)
                        .build());
            }
        }


        return null;
    }

    // 상품 삭제
    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Optional<Product> product = iProductRepository.findById(id);
        if(product.isPresent()) {
            Optional<CategoryProductList> categoryProductList = iCategoryProductListRepository.findByProduct(product.get());
            List<ProductOption> productOptions = iProductOptionRepository.findAllByProduct(product.get());

            if(categoryProductList.isPresent() && !productOptions.isEmpty()) {
                iCategoryProductListRepository.deleteByProduct(product.get());
                iProductOptionRepository.deleteByProduct(product.get());
            }

            iProductRepository.deleteById(id);
        }
    }

    @Override
    public Product editProduct(ProductEditDto productEditDto) {
        Optional<Product> product = iProductRepository.findById(productEditDto.getId());
        Optional<CategoryLv4> categoryLv4 = iCategoryLv4Repository.findById(productEditDto.getCategoryLv4());
        Optional<CategoryLv3> categoryLv3 = iCategoryLv3Repository.findById(productEditDto.getCategoryLv3());
        Optional<CategoryLv2> categoryLv2 = iCategoryLv2Repository.findById(productEditDto.getCategoryLv2());
        Optional<CategoryLv1> categoryLv1 = iCategoryLv1Repository.findById(productEditDto.getCategoryLv1());

        Optional<CategoryProductList> categoryProductList = iCategoryProductListRepository.findByProduct(product.get());

        if(product.isPresent() && categoryLv4.isPresent() && categoryLv3.isPresent() && categoryLv2.isPresent() && categoryLv1.isPresent()) {
            Product editProduct = iProductRepository.save(Product.builder()
                    .id(productEditDto.getId())
                    .productName(productEditDto.getProductName())
                    .price(productEditDto.getPrice())
                    .description(productEditDto.getDescription())
                    .manufactureCompany(productEditDto.getManufactureCompany())
                    .discountRate(productEditDto.getDiscountRate())
                    .fee(productEditDto.getFee())
                    .adultCase(productEditDto.isAdultCase())
                    .build()
            );

            iCategoryProductListRepository.save(CategoryProductList.builder()
                    .id(categoryProductList.get().getId())
                    .categoryLv1(categoryLv1.get())
                    .categoryLv2(categoryLv2.get())
                    .categoryLv3(categoryLv3.get())
                    .categoryLv4(categoryLv4.get())
                    .product(editProduct)
                    .build());

            for(ProductOption productOption : productEditDto.getProductOptionList()) {
                iProductOptionRepository.save(ProductOption.builder()
                        .id(productOption.getId())
                        .product(editProduct)
                        .productOption1Name(productOption.getProductOption1Name())
                        .productOption1Contents(productOption.getProductOption1Contents())
                        .productOption2Name(productOption.getProductOption2Name())
                        .productOption2Contents(productOption.getProductOption2Contents())
                        .stock(productOption.getStock())
                        .build());
            }

            return editProduct;
        }

        return null;

    }
}
