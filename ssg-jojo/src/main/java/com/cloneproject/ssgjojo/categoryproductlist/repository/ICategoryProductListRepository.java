package com.cloneproject.ssgjojo.categoryproductlist.repository;

import com.cloneproject.ssgjojo.categoryproductlist.domain.CategoryProductList;
import com.cloneproject.ssgjojo.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICategoryProductListRepository extends JpaRepository<CategoryProductList, Long> {
    void deleteByProduct(Product product);
    Optional<CategoryProductList> findByProduct(Product product);

    List<CategoryProductList> findByCategoryLv2_Id(Long categoryLv2);

    @Query("select ct ,ct.product from CategoryProductList ct " +
            "where ct.categoryLv3.id =:categoryLv3")
    List<Product> findByCategoryLv3id(@Param("categoryLv3") Long categoryLv3);
}
