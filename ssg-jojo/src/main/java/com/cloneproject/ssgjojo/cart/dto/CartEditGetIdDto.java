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
    private List<CartProductListGetIdEditDto> cartProductListGetIdEditDtoList;
}
