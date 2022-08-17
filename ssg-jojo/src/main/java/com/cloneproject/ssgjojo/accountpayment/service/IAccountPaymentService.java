package com.cloneproject.ssgjojo.accountpayment.service;

import com.cloneproject.ssgjojo.accountpayment.domain.AccountPayment;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDeleteDto;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDto;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentOutputDto;

import java.util.List;

public interface IAccountPaymentService {

    AccountPaymentOutputDto addAccountPayment(AccountPaymentDto accountPaymentDto);
    List<AccountPaymentOutputDto> getAccountPaymentByUserId(Long id);
    List<AccountPayment> getAllAccountPayment();
    void deleteAccountPayment(AccountPaymentDeleteDto accountPaymentDeleteDto);
}
