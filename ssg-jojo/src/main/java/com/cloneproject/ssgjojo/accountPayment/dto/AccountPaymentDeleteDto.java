package com.cloneproject.ssgjojo.accountPayment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountPaymentDeleteDto {

    private Long id;
    private Long userId;
}

