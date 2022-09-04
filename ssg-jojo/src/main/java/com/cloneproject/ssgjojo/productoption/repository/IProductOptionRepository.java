package com.cloneproject.ssgjojo.productoption.repository;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.productoption.domain.ProductOption;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductOptionRepository extends JpaRepository<ProductOption, Long> {
    List<ProductOption> findAllByProduct(Product product);
    void deleteByProduct(Product product);
    void deleteAllByProduct(Product product);

    List<ProductOption> findAllByProductAndProductOption1Contents(Product product, String option1);

    @Query(value = "select distinct po.productOption1Contents from ProductOption po " +
            "where po.product.id = :productId ")
    List<String> getOpt1(@Param("productId") Long productId);

    @Query(value = "select po from ProductOption po " +
            "where po.product.id = :productId " +
            "and po.productOption1Contents = :optContents ")
    List<ProductOption> getByProductAndOptName(@Param("productId") Long productId, @Param("optContents") String optContents);
}
