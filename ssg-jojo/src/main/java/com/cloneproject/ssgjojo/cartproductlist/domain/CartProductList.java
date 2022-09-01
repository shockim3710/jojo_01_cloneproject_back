package com.cloneproject.ssgjojo.cartproductlist.domain;

import com.cloneproject.ssgjojo.cart.domain.Cart;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.productoption.domain.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int cartCount; // 하나의 상품을 몇개 주문하는지


    @ManyToOne
    private Product product;

    @ManyToOne
    private ProductOption productOption;

    @ManyToOne
    private Cart cart;
}
