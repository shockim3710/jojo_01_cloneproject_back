package com.cloneproject.ssgjojo.accountpayment.service;

import com.cloneproject.ssgjojo.accountpayment.domain.AccountPayment;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDeleteDto;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDto;

import java.util.List;

public interface IAccountPaymentService {

    AccountPayment addAccountPayment(AccountPaymentDto accountPaymentDto);
    List<AccountPayment> getAccountPaymentByUserId(Long id);
    List<AccountPayment> getAllAccountPayment();
    void deleteAccountPayment(AccountPaymentDeleteDto accountPaymentDeleteDto);
}
