package com.cloneproject.ssgjojo.accountpayment.service;

import com.cloneproject.ssgjojo.accountpayment.domain.AccountPayment;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDeleteDto;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDto;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentOutputDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface IAccountPaymentService {

    AccountPayment addAccountPayment(AccountPaymentDto accountPaymentDto, HttpServletRequest request);
    List<AccountPaymentOutputDto> getAccountPaymentByUserId(HttpServletRequest request);
    Optional<AccountPayment> deleteAccountPayment(AccountPaymentDeleteDto accountPaymentDeleteDto, HttpServletRequest request);
}
