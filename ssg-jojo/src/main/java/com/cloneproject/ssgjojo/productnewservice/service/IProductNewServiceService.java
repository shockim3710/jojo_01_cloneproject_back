package com.cloneproject.ssgjojo.productnewservice.service;

import com.cloneproject.ssgjojo.productnewservice.domain.ProductNewService;
import com.cloneproject.ssgjojo.productnewservice.dto.ProductNewServiceAddDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductNewServiceService {
    ProductNewService addProductNewService(ProductNewServiceAddDto productNewServiceAddDto, MultipartFile multipartFile);
    ProductNewService getProductNewServiceById(Long id);
    List<ProductNewService> getAllProductNewService();
    void deleteProductNewService(Long id);
    ProductNewService editProductNewService(ProductNewService productNewService);
}
