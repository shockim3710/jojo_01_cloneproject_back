package com.cloneproject.ssgjojo.payingcreditcard.controller;

import com.cloneproject.ssgjojo.payingcreditcard.domain.PayingCreditCard;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardDeleteDto;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardInputDto;
import com.cloneproject.ssgjojo.payingcreditcard.dto.PayingCreditCardOutputDto;
import com.cloneproject.ssgjojo.payingcreditcard.service.IPayingCreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PayingCreditCardController {

    private final IPayingCreditCardService iPayingCreditCardService;

    @PostMapping("/payingcreditcard/add")
    public PayingCreditCardOutputDto addPayingCreditCard(@RequestBody PayingCreditCardInputDto payingCreditCardInputDto) {
        return iPayingCreditCardService.addPayingCreditCard(payingCreditCardInputDto);
    }

    @GetMapping("/payingcreditcard/getAll")
    public List<PayingCreditCard> getAllPayingCreditCard() {
        return iPayingCreditCardService.getAllPayingCreditCard();
    }

    @GetMapping("/payingcreditcard/{id}")
    public List<PayingCreditCardOutputDto> getPayingCreditCardByUserId(@PathVariable Long id) {
        return iPayingCreditCardService.getPayingCreditCardByUserId(id);
    }

    @DeleteMapping("/payingcreditcard/delete")
    void deletePayingCreditCard(@RequestBody PayingCreditCardDeleteDto payingCreditCardDeleteDto) {
        iPayingCreditCardService.deletePayingCreditCard(payingCreditCardDeleteDto);
    }


}
