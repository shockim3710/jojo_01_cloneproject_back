package com.cloneproject.ssgjojo.accountpayment.controller;

import com.cloneproject.ssgjojo.accountpayment.domain.AccountPayment;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDeleteDto;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDto;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentOutputDto;
import com.cloneproject.ssgjojo.accountpayment.service.IAccountPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountPaymentController {

    private final IAccountPaymentService iAccountPaymentService;

    // 결제 계좌 등록
    @PostMapping("/accountpayment/add")
    public AccountPaymentOutputDto addAccountPayment(@RequestBody AccountPaymentDto accountPaymentDto, HttpServletRequest request) {
        return iAccountPaymentService.addAccountPayment(accountPaymentDto, request);
    }

    // 해당 유저의 결제 계좌 조회
    @GetMapping("/accountpayment/getAll")
    public List<AccountPaymentOutputDto> getAccountPaymentByUserId(HttpServletRequest request) {
        return iAccountPaymentService.getAccountPaymentByUserId(request);
    }

    // 결제 계좌 삭제
    @DeleteMapping("/accountpayment/delete")
    void deleteAccountPayment(@RequestBody AccountPaymentDeleteDto accountPaymentDeleteDto, HttpServletRequest request) {
        iAccountPaymentService.deleteAccountPayment(accountPaymentDeleteDto, request);
    }

}
