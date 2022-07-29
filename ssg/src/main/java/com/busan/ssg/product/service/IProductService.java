package com.busan.ssg.product.service;

import com.busan.ssg.product.domain.Product;

import java.util.List;

public interface IProductService {

    Product addProduct(Product product);

    Product getProductById(Long id);

    List<Product> getAll();
}
