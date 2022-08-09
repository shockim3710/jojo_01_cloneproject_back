package com.cloneproject.ssgjojo.payingCreditCard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayingCreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String creditCardName;         // 신용카드 이름

    @Column(nullable = false)
    private String creditCardCompany;      // 신용카드 회사명
}
