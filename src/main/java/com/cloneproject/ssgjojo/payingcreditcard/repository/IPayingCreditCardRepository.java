package com.cloneproject.ssgjojo.payingcreditcard.repository;

import com.cloneproject.ssgjojo.payingcreditcard.domain.PayingCreditCard;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPayingCreditCardRepository extends JpaRepository<PayingCreditCard, Long> {

    // user 한 명의 카드 목록 반환 시 repository에 List<> 형태로 반환하는 finadAllBy 함수 생성
    List<PayingCreditCard> findAllByUser(User user);
}
