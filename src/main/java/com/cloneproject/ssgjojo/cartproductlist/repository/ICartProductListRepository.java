package com.cloneproject.ssgjojo.cartproductlist.repository;

import com.cloneproject.ssgjojo.cart.domain.Cart;
import com.cloneproject.ssgjojo.cartproductlist.domain.CartProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartProductListRepository extends JpaRepository<CartProductList, Long> {
    List<CartProductList> findAllByCart(Cart cart);

    @Query("select cpl from CartProductList cpl, Cart ct " +
            "where cpl.cart.id = ct.id " +
            "and cpl.productOption.id = :optionId " +
            "and cpl.cart.id = :cart ")
    List<CartProductList> findProductOptionIdAndCartId(@Param("optionId") Long optionId, @Param("cart") Long cart);
}
