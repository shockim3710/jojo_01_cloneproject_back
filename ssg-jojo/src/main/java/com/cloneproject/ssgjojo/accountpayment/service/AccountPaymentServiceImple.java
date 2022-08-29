package com.cloneproject.ssgjojo.accountpayment.service;

import com.cloneproject.ssgjojo.accountpayment.domain.AccountPayment;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDeleteDto;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDto;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentOutputDto;
import com.cloneproject.ssgjojo.accountpayment.repository.IAccountPaymentRepository;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountPaymentServiceImple implements IAccountPaymentService{

    private final IAccountPaymentRepository iAccountPaymentRepository;
    private final IUserRepository iUserRepository;


    // 결제 계좌 등록
    @Override
    public AccountPaymentOutputDto addAccountPayment(AccountPaymentDto accountPaymentDto) {

        Optional<User> user = iUserRepository.findById(accountPaymentDto.getUserId());

        if(user.isPresent()) {
            AccountPayment accountPayment = iAccountPaymentRepository.save(AccountPayment.builder()
                    .accountNumber(accountPaymentDto.getAccountNumber())
                    .bank(accountPaymentDto.getBank())
                    .user(user.get())
                    .build());

            return AccountPaymentOutputDto.builder()
                    .id(accountPayment.getId())
                    .accountNumber(accountPayment.getAccountNumber())
                    .bank(accountPaymentDto.getBank())
                    .userId(accountPayment.getUser().getId())
                    .build();
        }

        return null;
    }

    // 해당 유저 id로 결제 계좌 조회
    @Override
    public List<AccountPaymentOutputDto> getAccountPaymentByUserId(Long id) {

        Optional<User> user = iUserRepository.findById(id);

        if(user.isPresent()) {
            List<AccountPayment> accountPaymentList = iAccountPaymentRepository.findAllByUser(user.get());
            List<AccountPaymentOutputDto> accountPaymentOutputDtoList = new ArrayList<>();

            if(!accountPaymentList.isEmpty()) {
                for(AccountPayment payment : accountPaymentList) {
                    accountPaymentOutputDtoList.add(AccountPaymentOutputDto.builder()
                            .id(payment.getId())
                            .bank(payment.getBank())
                            .accountNumber(payment.getAccountNumber())
                            .userId(payment.getUser().getId())
                            .build());
                }
            }

            return accountPaymentOutputDtoList;
        }

        return null;
    }


    // 결제 계좌 삭제
    @Override
    public void deleteAccountPayment(AccountPaymentDeleteDto accountPaymentDeleteDto) {

        Optional<AccountPayment> accountPayment = iAccountPaymentRepository.findById(accountPaymentDeleteDto.getId());
        Optional<User> user = iUserRepository.findById(accountPaymentDeleteDto.getUserId());

        if(user.isPresent() && accountPayment.isPresent()) {
                iAccountPaymentRepository.deleteById(accountPaymentDeleteDto.getId());
        }
    }
}
