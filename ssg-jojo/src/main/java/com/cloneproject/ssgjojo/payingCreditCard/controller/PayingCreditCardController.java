package com.cloneproject.ssgjojo.payingCreditCard.controller;

import com.cloneproject.ssgjojo.payingCreditCard.domain.PayingCreditCard;
import com.cloneproject.ssgjojo.payingCreditCard.dto.PayingCreditCardDeleteDto;
import com.cloneproject.ssgjojo.payingCreditCard.dto.PayingCreditCardInputDto;
import com.cloneproject.ssgjojo.payingCreditCard.service.IPayingCreditCardService;
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
    public PayingCreditCard addPayingCreditCard(@RequestBody PayingCreditCardInputDto payingCreditCardInputDto) {
        return iPayingCreditCardService.addPayingCreditCard(payingCreditCardInputDto);
    }

    @GetMapping("/payingcreditcard/getAll")
    public List<PayingCreditCard> getAllPayingCreditCard() {
        return iPayingCreditCardService.getAllPayingCreditCard();
    }

    @GetMapping("/payingcreditcard/{id}")
    public List<PayingCreditCard> getPayingCreditCardByUserId(@PathVariable Long id) {
        return iPayingCreditCardService.getPayingCreditCardByUserId(id);
    }

    @DeleteMapping("/payingcreditcard/delete")
    void deletePayingCreditCard(@RequestBody PayingCreditCardDeleteDto payingCreditCardDeleteDto) {
        iPayingCreditCardService.deletePayingCreditCard(payingCreditCardDeleteDto);
    }


}
