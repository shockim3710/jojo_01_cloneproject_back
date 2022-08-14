package com.cloneproject.ssgjojo.productphoto.repository;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.productoption.domain.ProductOption;
import com.cloneproject.ssgjojo.productphoto.domain.ProductPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductPhotoRepository extends JpaRepository<ProductPhoto, Long> {
    public List<ProductPhoto> findAllByProduct(Product product);

}
