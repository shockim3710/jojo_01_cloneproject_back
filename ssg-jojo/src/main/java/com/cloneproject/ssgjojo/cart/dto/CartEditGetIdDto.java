package com.cloneproject.ssgjojo.cart.dto;

import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListGetIdEditDto;
import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListGetIdEditDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartEditGetIdDto {
    private Long id;

    private Long user;

    private Long deliveryAddress;
    private String address; // 배송지

    private List<CartProductListGetIdEditDto> cartProductListGetIdEditDtoList;
}
