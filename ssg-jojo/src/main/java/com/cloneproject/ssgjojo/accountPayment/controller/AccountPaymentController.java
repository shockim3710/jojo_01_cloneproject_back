package com.cloneproject.ssgjojo.accountPayment.controller;

import com.cloneproject.ssgjojo.accountPayment.domain.AccountPayment;
import com.cloneproject.ssgjojo.accountPayment.dto.AccountPaymentDeleteDto;
import com.cloneproject.ssgjojo.accountPayment.dto.AccountPaymentDto;
import com.cloneproject.ssgjojo.accountPayment.service.IAccountPaymentService;
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
    public AccountPayment addAccountPayment(@RequestBody AccountPaymentDto accountPaymentDto) {
        return iAccountPaymentService.addAccountPayment(accountPaymentDto);
    }

    @GetMapping("/accountpayment/getAll")
    public List<AccountPayment> getAllAccountPayment() {
        return iAccountPaymentService.getAllAccountPayment();
    }

    @GetMapping("/accountpayment/{id}")
    public AccountPayment getAccountPaymentByUserId(@PathVariable Long id) {
        return iAccountPaymentService.getAccountPaymentByUserId(id);
    }

    @DeleteMapping("/accountpayment/delete")
    void deleteAccountPayment(@RequestBody AccountPaymentDeleteDto accountPaymentDeleteDto) {
        iAccountPaymentService.deleteAccountPayment(accountPaymentDeleteDto);
    }

}
