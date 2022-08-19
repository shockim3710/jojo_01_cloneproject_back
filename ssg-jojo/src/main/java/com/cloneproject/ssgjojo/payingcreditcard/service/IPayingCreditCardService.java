package com.cloneproject.ssgjojo.payingcreditcard.service;

import com.cloneproject.ssgjojo.payingcreditcard.domain.PayingCreditCard;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardDeleteDto;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardInputDto;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardOutputDto;

import java.util.List;

public interface IPayingCreditCardService {

    PayingCreditCardOutputDto addPayingCreditCard(PayingCreditCardInputDto payingCreditCardInputDto);
    List<PayingCreditCardOutputDto> getPayingCreditCardByUserId(Long id);
    List<PayingCreditCard> getAllPayingCreditCard();
    void deletePayingCreditCard(PayingCreditCardDeleteDto payingCreditCardDeleteDto);

}
