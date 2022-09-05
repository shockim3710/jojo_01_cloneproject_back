package com.cloneproject.ssgjojo.accountpayment.repository;

import com.cloneproject.ssgjojo.accountpayment.domain.AccountPayment;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAccountPaymentRepository extends JpaRepository<AccountPayment, Long> {

    List<AccountPayment> findAllByUser(User user);
}
