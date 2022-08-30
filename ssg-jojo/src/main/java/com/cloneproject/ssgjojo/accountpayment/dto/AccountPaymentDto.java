package com.cloneproject.ssgjojo.accountpayment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountPaymentDto {

    private String accountNumber;
    private String bank;
}
