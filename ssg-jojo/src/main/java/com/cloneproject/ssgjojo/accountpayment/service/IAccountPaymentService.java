package com.cloneproject.ssgjojo.accountpayment.service;

import com.cloneproject.ssgjojo.accountpayment.domain.AccountPayment;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDeleteDto;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDto;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentOutputDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAccountPaymentService {

    AccountPaymentOutputDto addAccountPayment(AccountPaymentDto accountPaymentDto, HttpServletRequest request);
    List<AccountPaymentOutputDto> getAccountPaymentByUserId(HttpServletRequest request);
    void deleteAccountPayment(AccountPaymentDeleteDto accountPaymentDeleteDto, HttpServletRequest request);
}
