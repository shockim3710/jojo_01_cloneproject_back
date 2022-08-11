package com.cloneproject.ssgjojo.productnewservice.service;

import com.cloneproject.ssgjojo.productnewservice.domain.ProductNewService;

import java.util.List;

public interface IProductNewServiceService {
    ProductNewService addProductNewService(ProductNewService productNewService);
    ProductNewService getProductNewServiceById(Long id);
    List<ProductNewService> getAllProductNewService();
    void deleteProductNewService(Long id);
    ProductNewService editProductNewService(ProductNewService productNewService);
}
