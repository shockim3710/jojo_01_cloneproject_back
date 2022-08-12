package com.cloneproject.ssgjojo.cart.domain;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int count; // 하나의 상품을 장바구니에 몇개 담았는지

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;
}
