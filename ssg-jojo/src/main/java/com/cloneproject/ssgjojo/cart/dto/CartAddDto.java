package com.cloneproject.ssgjojo.cart.dto;

import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListAddDto;
import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListAddDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartAddDto {
    private Long user;

    private Long deliveryAddress;
    private String address; // 배송지


    private List<CartProductListAddDto> cartProductListAddDtoList;
}
