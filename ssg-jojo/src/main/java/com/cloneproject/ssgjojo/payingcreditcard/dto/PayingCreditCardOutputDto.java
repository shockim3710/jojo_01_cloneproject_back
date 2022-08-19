package com.cloneproject.ssgjojo.payingcreditcard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayingCreditCardOutputDto {

    private Long id;
    private String creditCardName;
    private String creditCardCompany;
    private Long userId;
}
