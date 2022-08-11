package com.cloneproject.ssgjojo.deliveryAddress.domain;

import com.cloneproject.ssgjojo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address; // 배송지

    @Column(nullable = false)
    private boolean isDefaultDeliveryAddress; // 기본배송지 여부

    @ManyToOne
    private User user;

}
