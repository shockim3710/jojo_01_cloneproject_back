package com.cloneproject.ssgjojo.accountPayment.repository;

import com.cloneproject.ssgjojo.accountPayment.domain.AccountPayment;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAccountPaymentRepository extends JpaRepository<AccountPayment, Long> {

    public List<AccountPayment> findAllByUser(User user);
}
