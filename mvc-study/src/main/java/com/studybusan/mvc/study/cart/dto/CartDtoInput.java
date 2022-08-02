package com.studybusan.mvc.study.cart.dto;


import lombok.Data;

// input을 처리하기 위한 클래스
// => 불러와서 쓰고 갖다버리는, 순수하게 받기만 하는 용도
@Data
public class CartDtoInput {

    private Long userId;
    private Long productId;
    private int qty;
    private int price;

}
