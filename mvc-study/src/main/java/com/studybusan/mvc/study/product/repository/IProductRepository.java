package com.studybusan.mvc.study.product.repository;

import com.studybusan.mvc.study.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository <Product, Long> {
}
