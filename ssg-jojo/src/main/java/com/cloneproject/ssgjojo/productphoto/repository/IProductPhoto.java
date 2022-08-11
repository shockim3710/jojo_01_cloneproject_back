package com.cloneproject.ssgjojo.productphoto.repository;

import com.cloneproject.ssgjojo.productphoto.domain.ProductPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductPhoto extends JpaRepository<ProductPhoto, Long> {
}
