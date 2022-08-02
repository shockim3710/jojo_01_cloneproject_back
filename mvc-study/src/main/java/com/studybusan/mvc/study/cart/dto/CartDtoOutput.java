package com.studybusan.mvc.study.cart.dto;

import com.studybusan.mvc.study.cart.model.Cart;
import com.studybusan.mvc.study.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDtoOutput {

    private Long id;
    private String productName;
    private int price;
    private int qty;

    public static CartDtoOutput of(Cart cart, Product product) {
        return CartDtoOutput.builder()
                .id(cart.getId())
                .productName(cart.getProduct().getName())
                .qty(cart.getQty())
                .price(cart.getProduct().getPrice())
                .build();
    }

}
