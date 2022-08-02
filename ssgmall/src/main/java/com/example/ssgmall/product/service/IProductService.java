package com.example.ssgmall.product.service;

import com.example.ssgmall.product.domain.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAll();
}
