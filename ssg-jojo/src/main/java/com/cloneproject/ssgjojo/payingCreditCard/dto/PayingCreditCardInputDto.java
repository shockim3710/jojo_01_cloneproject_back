package com.cloneproject.ssgjojo.payingCreditCard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayingCreditCardInputDto {

    private String creditCardName;
    private String creditCardCompany;

    private Long userId;
}
