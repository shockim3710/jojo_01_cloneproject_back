package com.cloneproject.ssgjojo.categoryproductlist.repository;

import com.cloneproject.ssgjojo.categoryproductlist.domain.CategoryProductList;
import com.cloneproject.ssgjojo.product.domain.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICategoryProductListRepository extends JpaRepository<CategoryProductList, Long> {
    void deleteByProduct(Product product);
    Optional<CategoryProductList> findByProduct(Product product);

    List<CategoryProductList> findByCategoryLv2_Id(Long categoryLv2);

    @Query("select ct.product from CategoryProductList ct " +
            "where ct.categoryLv1.id =:categoryLv1")
    List<Product> findByCategoryLv1id(@Param("categoryLv1") Long categoryLv1, Pageable pageable);

    @Query("select ct.product from CategoryProductList ct " +
            "where ct.categoryLv2.id =:categoryLv2")
    List<Product> findByCategoryLv2id(@Param("categoryLv2") Long categoryLv2, Pageable pageable);

    @Query("select ct.product from CategoryProductList ct " +
            "where ct.categoryLv3.id =:categoryLv3")
    List<Product> findByCategoryLv3id(@Param("categoryLv3") Long categoryLv3, Pageable pageable);

    @Query("select ct.product from CategoryProductList ct " +
            "where ct.categoryLv4.id =:categoryLv4")
    List<Product> findByCategoryLv4id(@Param("categoryLv4") Long categoryLv4, Pageable pageable);
}
