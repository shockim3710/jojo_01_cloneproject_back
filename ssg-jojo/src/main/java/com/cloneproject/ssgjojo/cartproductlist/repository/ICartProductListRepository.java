package com.cloneproject.ssgjojo.cartproductlist.repository;

import com.cloneproject.ssgjojo.cart.domain.Cart;
import com.cloneproject.ssgjojo.cartproductlist.domain.CartProductList;
import com.cloneproject.ssgjojo.orders.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartProductListRepository extends JpaRepository<CartProductList, Long> {

    List<CartProductList> findAllByCart(Cart cart);

}
