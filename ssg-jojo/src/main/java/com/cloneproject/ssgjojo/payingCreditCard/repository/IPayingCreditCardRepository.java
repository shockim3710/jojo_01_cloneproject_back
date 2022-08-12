package com.cloneproject.ssgjojo.payingCreditCard.repository;

import com.cloneproject.ssgjojo.payingCreditCard.domain.PayingCreditCard;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPayingCreditCardRepository extends JpaRepository<PayingCreditCard, Long> {

    // user 한 명의 카드 목록 반환 시 repository에 List<> 형태로 반환하는 finadAllBy 함수 생성
    public List<PayingCreditCard> findAllByUser(User user);
}
