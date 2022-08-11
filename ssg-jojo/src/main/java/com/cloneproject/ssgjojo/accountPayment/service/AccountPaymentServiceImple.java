package com.cloneproject.ssgjojo.accountPayment.service;

import com.cloneproject.ssgjojo.accountPayment.domain.AccountPayment;
import com.cloneproject.ssgjojo.accountPayment.dto.AccountPaymentDeleteDto;
import com.cloneproject.ssgjojo.accountPayment.dto.AccountPaymentDto;
import com.cloneproject.ssgjojo.accountPayment.repository.IAccountPaymentRepository;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountPaymentServiceImple implements IAccountPaymentService{

    private final IAccountPaymentRepository iAccountPaymentRepository;
    private final IUserRepository iUserRepository;


    @Override
    public AccountPayment addAccountPayment(AccountPaymentDto accountPaymentDto) {

        Optional<User> user = iUserRepository.findById(accountPaymentDto.getUserId());

        if(user.isPresent()) {
            return iAccountPaymentRepository.save(AccountPayment.builder()
                    .accountNumber(accountPaymentDto.getAccountNumber())
                    .bank(accountPaymentDto.getBank())
                    .user(user.get())
                    .build());
        }

        return null;
    }

    @Override
    public AccountPayment getAccountPaymentByUserId(Long id) {

        Optional<AccountPayment> accountPayment = iAccountPaymentRepository.findById(id);
        Optional<User> user = iUserRepository.findById(id);

        if(accountPayment.isPresent() && user.isPresent()) {
            return accountPayment.get();
        }

        return null;
    }

    @Override
    public List<AccountPayment> getAllAccountPayment() {
        return iAccountPaymentRepository.findAll();
    }

    @Override
    public void deleteAccountPayment(AccountPaymentDeleteDto accountPaymentDeleteDto) {

        Optional<AccountPayment> accountPayment = iAccountPaymentRepository.findById(accountPaymentDeleteDto.getId());
        Optional<User> user = iUserRepository.findById(accountPaymentDeleteDto.getUserId());

        if(user.isPresent() && accountPayment.isPresent()) {
                iAccountPaymentRepository.deleteById(accountPaymentDeleteDto.getId());
        }
    }
}
