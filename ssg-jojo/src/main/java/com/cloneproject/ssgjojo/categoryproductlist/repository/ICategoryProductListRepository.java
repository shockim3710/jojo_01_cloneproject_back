package com.cloneproject.ssgjojo.categoryProductList.repository;

import com.cloneproject.ssgjojo.categoryProductList.domain.CategoryProductList;
import com.cloneproject.ssgjojo.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryProductListRepository extends JpaRepository<CategoryProductList, Long> {
    void deleteByProduct(Product product);
    Optional<CategoryProductList> findByProduct(Product product);
}
