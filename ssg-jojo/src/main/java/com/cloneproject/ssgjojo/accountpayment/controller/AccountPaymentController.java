package com.cloneproject.ssgjojo.accountpayment.controller;

import com.cloneproject.ssgjojo.accountpayment.domain.AccountPayment;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDeleteDto;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentDto;
import com.cloneproject.ssgjojo.accountpayment.dto.AccountPaymentOutputDto;
import com.cloneproject.ssgjojo.accountpayment.service.IAccountPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountPaymentController {

    private final IAccountPaymentService iAccountPaymentService;

    @PostMapping("/accountpayment/add")
    public AccountPaymentOutputDto addAccountPayment(@RequestBody AccountPaymentDto accountPaymentDto) {
        return iAccountPaymentService.addAccountPayment(accountPaymentDto);
    }

    @GetMapping("/accountpayment/{id}")
    public List<AccountPaymentOutputDto> getAccountPaymentByUserId(@PathVariable Long id) {
        return iAccountPaymentService.getAccountPaymentByUserId(id);
    }

    @DeleteMapping("/accountpayment/delete")
    void deleteAccountPayment(@RequestBody AccountPaymentDeleteDto accountPaymentDeleteDto) {
        iAccountPaymentService.deleteAccountPayment(accountPaymentDeleteDto);
    }

}
