package com.cloneproject.ssgjojo.accountPayment.repository;

import com.cloneproject.ssgjojo.accountPayment.domain.AccountPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountPaymentRepository extends JpaRepository<AccountPayment, Long> {
}
