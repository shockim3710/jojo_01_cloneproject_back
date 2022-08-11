package com.cloneproject.ssgjojo.categoryProductList.repository;

import com.cloneproject.ssgjojo.categoryProductList.domain.CategoryProductList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryProductListRepository extends JpaRepository<CategoryProductList, Long> {
    void deleteByProduct(Long id);
    CategoryProductList findByProduct(Long id);
}
