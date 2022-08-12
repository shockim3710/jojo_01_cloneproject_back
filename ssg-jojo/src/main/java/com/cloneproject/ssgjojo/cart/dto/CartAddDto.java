package com.cloneproject.ssgjojo.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartAddDto {
    private int count; // 하나의 상품을 장바구니에 몇개 담았는지

    private Long user;
    private Long product;
}
