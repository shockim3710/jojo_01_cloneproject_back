package com.cloneproject.ssgjojo.productoption.repository;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.productoption.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductOptionRepository extends JpaRepository<ProductOption, Long> {
    public List<ProductOption> findAllByProduct(Product product);
    public void deleteByProduct(Product product);
}
