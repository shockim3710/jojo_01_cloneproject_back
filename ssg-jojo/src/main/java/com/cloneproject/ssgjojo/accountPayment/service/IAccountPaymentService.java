package com.cloneproject.ssgjojo.accountPayment.service;

import com.cloneproject.ssgjojo.accountPayment.domain.AccountPayment;
import com.cloneproject.ssgjojo.accountPayment.dto.AccountPaymentDeleteDto;
import com.cloneproject.ssgjojo.accountPayment.dto.AccountPaymentDto;

import java.util.List;

public interface IAccountPaymentService {

    AccountPayment addAccountPayment(AccountPaymentDto accountPaymentDto);
    AccountPayment getAccountPaymentByUserId(Long id);
    List<AccountPayment> getAllAccountPayment();
    void deleteAccountPayment(AccountPaymentDeleteDto accountPaymentDeleteDto);
}
