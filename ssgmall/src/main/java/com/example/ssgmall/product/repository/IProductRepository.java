package com.example.ssgmall.product.repository;

import com.example.ssgmall.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
