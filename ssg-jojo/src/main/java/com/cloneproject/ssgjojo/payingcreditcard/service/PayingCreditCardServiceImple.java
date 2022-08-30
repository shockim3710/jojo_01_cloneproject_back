package com.cloneproject.ssgjojo.payingcreditcard.service;

import com.cloneproject.ssgjojo.jwt.JwtTokenProvider;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PayingCreditCardServiceImple implements IPayingCreditCardService {

    private final IPayingCreditCardRepository iPayingCreditCardRepository;
    private final IUserRepository iUserRepository;
    private final JwtTokenProvider jwtTokenProvider;


    // 결제 카드 등록
    @Override
    public PayingCreditCardOutputDto addPayingCreditCard(PayingCreditCardInputDto payingCreditCardInputDto, HttpServletRequest request) {

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);

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
                    .build();
        }
        return null;
    }

    // 해당 유저의 결제 카드 조회
    @Override
    public List<PayingCreditCardOutputDto> getPayingCreditCardByUserId(HttpServletRequest request) {

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        // user에 대한 데이터 유효성 검증 후 있으면 repository finaAllBy 통해 user의 데이터 반환
        Optional<User> user = iUserRepository.findById(userId);

        if(user.isPresent()) {

            List<PayingCreditCard> payingCreditCardList = iPayingCreditCardRepository.findAllByUser(user.get());
            List<PayingCreditCardOutputDto> payingCreditCardOutputDtoList = new ArrayList<>();

            if(!payingCreditCardList.isEmpty()) {
                for(PayingCreditCard payingCreditCard : payingCreditCardList) {
                    payingCreditCardOutputDtoList.add(PayingCreditCardOutputDto.builder()
                            .id(payingCreditCard.getId())
                            .creditCardName(payingCreditCard.getCreditCardName())
                            .creditCardCompany(payingCreditCard.getCreditCardCompany())
                            .build());
                }
            }

            return payingCreditCardOutputDtoList;
        }

        return null;
    }

    // 결제 카드 삭제
    @Override
    public boolean deletePayingCreditCard(PayingCreditCardDeleteDto payingCreditCardDeleteDto, HttpServletRequest request) {
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);

        Optional<PayingCreditCard> payingCreditCard = iPayingCreditCardRepository.findById(payingCreditCardDeleteDto.getId());

        if(user.isPresent() && payingCreditCard.isPresent()) {
            if(user.get().getId() == payingCreditCard.get().getUser().getId()) {
                iPayingCreditCardRepository.deleteById(payingCreditCardDeleteDto.getId());
                return true;
            }
        }

        return false;
    }
}
