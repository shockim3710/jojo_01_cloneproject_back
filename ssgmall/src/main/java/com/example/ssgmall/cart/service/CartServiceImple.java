package com.example.ssgmall.cart.service;

import com.example.ssgmall.cart.domain.Cart;
import com.example.ssgmall.cart.dto.CartDto;
import com.example.ssgmall.cart.repository.ICartRepository;
import com.example.ssgmall.product.repository.IProductRepository;
import com.example.ssgmall.user.repository.IUserRepository;
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
    public List<Cart> getAllCart() { return iCartRepository.findAll(); }

    @Override
    public List<Cart> getAllCartByUserId(Long id) { return iCartRepository.findAllByUserId(id); }
}
