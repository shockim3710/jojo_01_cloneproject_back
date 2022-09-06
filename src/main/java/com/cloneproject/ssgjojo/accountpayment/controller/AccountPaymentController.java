package com.cloneproject.ssgjojo.accountpayment.controller;

import com.cloneproject.ssgjojo.accountpayment.domain.AccountPayment;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDeleteDto;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDto;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentOutputDto;
import com.cloneproject.ssgjojo.accountpayment.service.IAccountPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountPaymentController {

    private final IAccountPaymentService iAccountPaymentService;

    // 결제 계좌 등록
    @PostMapping("/accountpayment/add")
    public ResponseEntity<?> addAccountPayment(@RequestBody AccountPaymentDto accountPaymentDto, HttpServletRequest request) {
        AccountPayment accountPayment = iAccountPaymentService.addAccountPayment(accountPaymentDto, request);

        if(accountPayment!=null){
            return ResponseEntity.status(200).body("결제계좌가 등록되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    // 해당 유저의 결제 계좌 조회
    @GetMapping("/accountpayment/getAll")
    public ResponseEntity<?> getAccountPaymentByUserId(HttpServletRequest request) {
        List<AccountPaymentOutputDto> accountPayment = iAccountPaymentService.getAccountPaymentByUserId(request);

        if(accountPayment!=null){
            return ResponseEntity.status(200).body(accountPayment);
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    // 결제 계좌 삭제
    @DeleteMapping("/accountpayment/delete")
    public ResponseEntity<?> deleteAccountPayment(@RequestBody AccountPaymentDeleteDto accountPaymentDeleteDto, HttpServletRequest request) {
        Optional<AccountPayment> accountPayment = iAccountPaymentService.deleteAccountPayment(accountPaymentDeleteDto, request);

        if(accountPayment.isPresent()){
            return ResponseEntity.status(200).body("결제계좌가 삭제되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

}
