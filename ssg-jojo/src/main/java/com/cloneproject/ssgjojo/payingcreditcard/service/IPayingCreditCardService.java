package com.cloneproject.ssgjojo.payingcreditcard.service;

import com.cloneproject.ssgjojo.payingcreditcard.domain.PayingCreditCard;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardDeleteDto;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardInputDto;

import java.util.List;

public interface IPayingCreditCardService {

    PayingCreditCard addPayingCreditCard(PayingCreditCardInputDto payingCreditCardInputDto);
    List<PayingCreditCard> getPayingCreditCardByUserId(Long id);
    List<PayingCreditCard> getAllPayingCreditCard();
    void deletePayingCreditCard(PayingCreditCardDeleteDto payingCreditCardDeleteDto);

}
