package com.cloneproject.ssgjojo.cart.dto;

import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListAddDto;
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
    private List<CartProductListAddDto> cartProductListAddDtoList;
}
