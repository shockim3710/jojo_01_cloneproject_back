package com.cloneproject.ssgjojo.categoryProductList.repository;

import com.cloneproject.ssgjojo.categoryLv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categoryLv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categoryLv3.domain.CategoryLv3;
import com.cloneproject.ssgjojo.categoryLv4.domain.CategoryLv4;
import com.cloneproject.ssgjojo.categoryProductList.domain.CategoryProductList;
import com.cloneproject.ssgjojo.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryProductListRepository extends JpaRepository<CategoryProductList, Long> {
    void deleteByProduct(Product product);
    Optional<CategoryProductList> findByProduct(Product product);
}
