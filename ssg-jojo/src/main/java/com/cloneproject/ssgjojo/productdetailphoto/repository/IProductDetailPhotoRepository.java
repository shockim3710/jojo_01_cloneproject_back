package com.cloneproject.ssgjojo.productdetailphoto.repository;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.productdetailphoto.domain.ProductDetailPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductDetailPhotoRepository extends JpaRepository<ProductDetailPhoto, Long> {
    List<ProductDetailPhoto> findAllByProduct(Product product);
    void deleteAllByProduct(Product product);

}
