package com.busan.ssg.cart.service;

import com.busan.ssg.cart.domain.Cart;
import com.busan.ssg.cart.dto.CartDto;
import com.busan.ssg.cart.repository.ICartRepository;
import com.busan.ssg.product.repository.IProductRepository;
import com.busan.ssg.product.service.IProductService;
import com.busan.ssg.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImple implements ICartService{

    private final ICartRepository iCartRepository;
    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;


    @Override
    public Cart addCart(CartDto cartDto) {
        return iCartRepository.save(
                Cart.builder()
                        .product(iProductRepository.findById(cartDto.getProductId()).get())
                        .user(iUserRepository.findById(cartDto.getUserId()).get())
                        .build()
        );
    }

    @Override
    public List<Cart> getAllCart() {
        return iCartRepository.findAll();
    }

    @Override
    public List<Cart> getAllCartByUserId(Long id) {
        return iCartRepository.findAllByUserId(id);
    }
}
