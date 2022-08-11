package com.cloneproject.ssgjojo.cart.dto;

public class CartEditGetIdDto {
    private Long id;
    private int count; // 하나의 상품을 장바구니에 몇개 담았는지 (장바구니 수정)

    private Long user;
    private Long product;
}
