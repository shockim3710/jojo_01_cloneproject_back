package com.cloneproject.ssgjojo.cartproductlist.service;

import com.cloneproject.ssgjojo.cart.repository.ICartRepository;
import com.cloneproject.ssgjojo.cartproductlist.repository.ICartProductListRepository;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.productoption.repository.IProductOptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartProductListServiceImple implements ICartProductListService {

    private final ICartProductListRepository iCartProductListRepository;
    private final IProductRepository iProductRepository;
    private final IProductOptionRepository iProductOptionRepository;
    private final ICartRepository iCartRepository;

}
