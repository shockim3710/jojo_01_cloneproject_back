package com.example.ssgmall.product.service;

import com.example.ssgmall.product.domain.Product;
import com.example.ssgmall.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepository iProductRepository;

    @Override
    public Product addProduct(Product product) { return iProductRepository.save(product); }

    @Override
    public Product getProductById(Long id) { return iProductRepository.findById(id).get(); }

    @Override
    public List<Product> getAll() { return iProductRepository.findAll(); }
}
