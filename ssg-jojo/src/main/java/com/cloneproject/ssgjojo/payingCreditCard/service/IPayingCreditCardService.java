package com.cloneproject.ssgjojo.payingCreditCard.service;

import com.cloneproject.ssgjojo.payingCreditCard.domain.PayingCreditCard;
import com.cloneproject.ssgjojo.payingCreditCard.dto.PayingCreditCardDeleteDto;
import com.cloneproject.ssgjojo.payingCreditCard.dto.PayingCreditCardInputDto;

import java.util.List;

public interface IPayingCreditCardService {

    PayingCreditCard addPayingCreditCard(PayingCreditCardInputDto payingCreditCardInputDto);
    List<PayingCreditCard> getPayingCreditCardByUserId(Long id);
    List<PayingCreditCard> getAllPayingCreditCard();
    void deletePayingCreditCard(PayingCreditCardDeleteDto payingCreditCardDeleteDto);

}
