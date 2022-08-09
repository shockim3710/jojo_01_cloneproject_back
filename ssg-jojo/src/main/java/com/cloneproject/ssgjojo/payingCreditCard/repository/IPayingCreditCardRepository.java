package com.cloneproject.ssgjojo.payingCreditCard.repository;

import com.cloneproject.ssgjojo.payingCreditCard.domain.PayingCreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPayingCreditCardRepository extends JpaRepository<PayingCreditCard, Long> {
}
