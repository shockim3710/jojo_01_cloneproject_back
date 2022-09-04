package com.cloneproject.ssgjojo.payingcreditcard.controller;

import com.cloneproject.ssgjojo.payingcreditcard.domain.PayingCreditCard;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardDeleteDto;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardInputDto;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardOutputDto;
import com.cloneproject.ssgjojo.payingcreditcard.service.IPayingCreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PayingCreditCardController {

    private final IPayingCreditCardService iPayingCreditCardService;

    // 결제 카드 등록
    @PostMapping("/payingcreditcard/add")
    public PayingCreditCardOutputDto addPayingCreditCard(@RequestBody PayingCreditCardInputDto payingCreditCardInputDto, HttpServletRequest request) {
        return iPayingCreditCardService.addPayingCreditCard(payingCreditCardInputDto, request);
    }

    // 유저별 결제 카드 조회
    @GetMapping("/payingcreditcard")
    public List<PayingCreditCardOutputDto> getPayingCreditCardByUserId(HttpServletRequest request) {
        return iPayingCreditCardService.getPayingCreditCardByUserId(request);
    }

    // 결제 카드 삭제
    @DeleteMapping("/payingcreditcard/delete")
    boolean deletePayingCreditCard(@RequestBody PayingCreditCardDeleteDto payingCreditCardDeleteDto, HttpServletRequest request) {
        return iPayingCreditCardService.deletePayingCreditCard(payingCreditCardDeleteDto, request);
    }


}
