package com.studybusan.mvc.study.cart.service;

import com.studybusan.mvc.study.cart.dto.CartDtoInput;
import com.studybusan.mvc.study.cart.dto.CartDtoOutput;
import com.studybusan.mvc.study.cart.model.Cart;
import com.studybusan.mvc.study.cart.repository.ICartRepository;
import com.studybusan.mvc.study.product.repository.IProductRepository;
import com.studybusan.mvc.study.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImple implements ICartService{

    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;
    private final ICartRepository iCartRepository;

    @Override
    public Cart addCart(CartDtoInput cartDtoInput) {
        return iCartRepository.save (Cart.builder()
                .product(iProductRepository.findById(cartDtoInput.getProductId()).get())
                .user(iUserRepository.findById(cartDtoInput.getUserId()).get())
                .qty(cartDtoInput.getQty())
                .build());
    }

    @Override
    public List<CartDtoOutput> getAll() {

        List<Cart> cartList = iCartRepository.findAll();
/*        List<CartDtoOutput> cartDtoOutputList = new ArrayList<>();

        return cartList.stream().map( cart -> {
            cartDtoOutputList.add(
                    CartDtoOutput.builder()
                            .id(cart.getId())
                            .productName(cart.getProduct().getName())
                            .qty(cart.getQty())
                            .price(cart.getProduct().getPrice())
                            .build()
            );
        }); */

        return null;

    }


    @Override
    public CartDtoOutput getCartById(Long id) {

        // 내가 불러들인 상품이랑 유저 들어있음
        Cart cart = iCartRepository.findById(id).get();
        // Ouput에 들어가는 내용 => Cart List 통해서 조회되는 것
        return CartDtoOutput.builder()
                 .id(cart.getId())
                 .productName(cart.getProduct().getName())
                 .qty(cart.getQty())
                 .price(cart.getProduct().getPrice())
                 .build();

    }


}
