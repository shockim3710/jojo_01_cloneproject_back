package com.cloneproject.ssgjojo.cartproductlist.repository;

import com.cloneproject.ssgjojo.cart.domain.Cart;
import com.cloneproject.ssgjojo.cartproductlist.domain.CartProductList;
import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICartProductListRepository extends JpaRepository<CartProductList, Long> {

    List<CartProductList> findAllByCart(Cart cart);
    Optional<CartProductList> findByCartId(Long cartId);


//    @Query("select ct.product from CategoryProductList ct " +
//            "where ct.categoryLv1.id =:categoryLv1")
//    List<Product> findByCategoryLv1id(@Param("categoryLv1") Long categoryLv1);

    @Query("select ct.productOption from CartProductList ct " +
            "where ct.productOption.id = :optionId " +
            "and ct.cart.user.id = :user ")
    List<CartProductList> findTest(@Param("optionId") Long optionId, @Param("user") Long user);

}
