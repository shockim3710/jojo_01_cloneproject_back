package com.cloneproject.ssgjojo.payingcreditcard.service;

import com.cloneproject.ssgjojo.payingcreditcard.domain.PayingCreditCard;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardDeleteDto;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardInputDto;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardOutputDto;
import com.cloneproject.ssgjojo.payingcreditcard.repository.IPayingCreditCardRepository;
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
public class PayingCreditCardServiceImple implements IPayingCreditCardService {

    private final IPayingCreditCardRepository iPayingCreditCardRepository;
    private final IUserRepository iUserRepository;


    @Override
    public PayingCreditCardOutputDto addPayingCreditCard(PayingCreditCardInputDto payingCreditCardInputDto) {

        Optional<User> user = iUserRepository.findById(payingCreditCardInputDto.getUserId());

        if(user.isPresent()) {
            PayingCreditCard payingCreditCard = iPayingCreditCardRepository.save(PayingCreditCard.builder()
                    .creditCardName(payingCreditCardInputDto.getCreditCardName())
                    .creditCardCompany(payingCreditCardInputDto.getCreditCardCompany())
                    .user(user.get())
                    .build());

            return PayingCreditCardOutputDto.builder()
                    .id(payingCreditCard.getId())
                    .creditCardName(payingCreditCard.getCreditCardName())
                    .creditCardCompany(payingCreditCard.getCreditCardCompany())
                    .userId(payingCreditCard.getUser().getId())
                    .build();
        }
        return null;
    }

    @Override
    public List<PayingCreditCardOutputDto> getPayingCreditCardByUserId(Long id) {

        // user에 대한 데이터 유효성 검증 후 있으면 repository finaAllBy 통해 user의 데이터 반환
        Optional<User> user = iUserRepository.findById(id);

        if(user.isPresent()) {

            List<PayingCreditCard> payingCreditCardList = iPayingCreditCardRepository.findAllByUser(user.get());
            List<PayingCreditCardOutputDto> payingCreditCardOutputDtoList = new ArrayList<>();

            if(!payingCreditCardList.isEmpty()) {
                for(PayingCreditCard payingCreditCard : payingCreditCardList) {
                    payingCreditCardOutputDtoList.add(PayingCreditCardOutputDto.builder()
                            .id(payingCreditCard.getId())
                            .creditCardName(payingCreditCard.getCreditCardName())
                            .creditCardCompany(payingCreditCard.getCreditCardCompany())
                            .userId(payingCreditCard.getUser().getId())
                            .build());
                }
            }

            return payingCreditCardOutputDtoList;
        }

        return null;
    }

    @Override
    public List<PayingCreditCard> getAllPayingCreditCard() {
        return iPayingCreditCardRepository.findAll();
    }

    @Override
    public void deletePayingCreditCard(PayingCreditCardDeleteDto payingCreditCardDeleteDto) {

        Optional<User> user = iUserRepository.findById(payingCreditCardDeleteDto.getUserId());
        Optional<PayingCreditCard> payingCreditCard = iPayingCreditCardRepository.findById(payingCreditCardDeleteDto.getId());

        if(user.isPresent() && payingCreditCard.isPresent()) {
            iPayingCreditCardRepository.deleteById(payingCreditCardDeleteDto.getId());
        }
    }
}
