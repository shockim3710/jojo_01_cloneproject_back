package com.cloneproject.ssgjojo.payingcreditcard.controller;

import com.cloneproject.ssgjojo.payingcreditcard.domain.PayingCreditCard;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardDeleteDto;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardInputDto;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardOutputDto;
import com.cloneproject.ssgjojo.payingcreditcard.service.IPayingCreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addPayingCreditCard(@RequestBody PayingCreditCardInputDto payingCreditCardInputDto, HttpServletRequest request) {
        PayingCreditCard payingCreditCard = iPayingCreditCardService.addPayingCreditCard(payingCreditCardInputDto, request);

        if(payingCreditCard!=null){
            return ResponseEntity.status(200).body("결제카드가 등록되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    // 유저별 결제 카드 조회
    @GetMapping("/payingcreditcard")
    public ResponseEntity<?> getPayingCreditCardByUserId(HttpServletRequest request) {
        List<PayingCreditCardOutputDto> payingCreditCard = iPayingCreditCardService.getPayingCreditCardByUserId(request);

        if(payingCreditCard!=null){
            return ResponseEntity.status(200).body(payingCreditCard);
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    // 결제 카드 삭제
    @DeleteMapping("/payingcreditcard/delete")
    public ResponseEntity<?> deletePayingCreditCard(@RequestBody PayingCreditCardDeleteDto payingCreditCardDeleteDto, HttpServletRequest request) {
        boolean payingCreditCard = iPayingCreditCardService.deletePayingCreditCard(payingCreditCardDeleteDto, request);

        if(payingCreditCard == true){
            return ResponseEntity.status(200).body("결제카드가 삭제되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

}
