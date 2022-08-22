package com.cloneproject.ssgjojo.deliveryaddress.domain;

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

    @Column(nullable = false, name = "is_default_address")
    private boolean whetherDefaultAddress; // 기본배송지 여부

    @Column(nullable = false, name = "is_only_this_time")
    private boolean whetherOnlyThisTime; // 이번만배송지 여부

    @ManyToOne
    private User user;

}
