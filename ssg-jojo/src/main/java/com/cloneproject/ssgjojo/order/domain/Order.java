package com.cloneproject.ssgjojo.order.domain;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int count; // 하나의 상품을 몇개 주문하는지

    @Column(nullable = false)
    private Timestamp orderDate; // 주문 날짜

    @Column(nullable = false)
    private Long orderPrice; // 주문한 총 가격

    @Column(nullable = false)
    private boolean isExchange; // 주문자 이름, 주문자 전화번호, 배송지 변경여부

    @Column(nullable = false)
    private boolean isRefund; // 환불여부

    @Column(nullable = false)
    private String orderName; // 주문자 이름

    @Column(nullable = false)
    private String orderPhone; // 주문자 전화번호

    @Column(nullable = false)
    private Timestamp deliveryDate; // 배송날짜
    // 타임스탬프는 시간을 더해주거나 빼주는 그런게 따로 없어서
    // Calendar 객체를 사용

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;
}
