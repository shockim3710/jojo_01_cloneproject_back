package com.example.ssgmall.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// userId랑 productId 가져오는 중간 매개체
public class CartDto {
    private Long userId;
    private Long productId;
}
