package com.cloneproject.ssgjojo.accountpayment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountPaymentOutputDto {
    private Long id;
    private String accountNumber;
    private String bank;

    private Long userId;
}
