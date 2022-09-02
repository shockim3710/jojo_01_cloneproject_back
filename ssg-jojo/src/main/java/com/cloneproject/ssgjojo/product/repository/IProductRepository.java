package com.cloneproject.ssgjojo.product.repository;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.dto.ProductListDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {

//    @Query(value = "select search(ps) from Product ps where ps.product.name=:id ")
//    List<ProductListDto> productSearch(Product product);

    List<Product> findByProductNameContaining(String name);

    @Query(value = "select pr from Product pr")
    List<Product> getProduct(Pageable pr);
}
