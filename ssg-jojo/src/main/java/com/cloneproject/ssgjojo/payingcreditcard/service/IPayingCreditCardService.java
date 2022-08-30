package com.cloneproject.ssgjojo.payingcreditcard.service;

import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDto;
import com.cloneproject.ssgjojo.payingcreditcard.domain.PayingCreditCard;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardDeleteDto;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardInputDto;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardOutputDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IPayingCreditCardService {

    PayingCreditCardOutputDto addPayingCreditCard(PayingCreditCardInputDto payingCreditCardInputDto, HttpServletRequest request);
    List<PayingCreditCardOutputDto> getPayingCreditCardByUserId(HttpServletRequest request);
    boolean deletePayingCreditCard(PayingCreditCardDeleteDto payingCreditCardDeleteDto, HttpServletRequest request);

}
