package com.busan.ssg.product.repository;

import com.busan.ssg.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {

}
