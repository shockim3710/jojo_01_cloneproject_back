package com.cloneproject.ssgjojo.orders.domain;

import com.cloneproject.ssgjojo.deliveryaddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.util.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Orders extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long ordersPrice; // 주문한 총 가격

    @Column(nullable = false, name = "is_exchange")
    private boolean whetherExchange; // 주문자 이름, 주문자 전화번호, 주문자 이메일, 배송지, 배송요청사항 변경여부

    @Column(nullable = false)
    private String ordersName; // 주문자 이름

    @Column(nullable = false)
    private String ordersPhone; // 주문자 전화번호

    @Column(nullable = false)
    private String ordersEmail; // 주문자 이메일

    @Column(nullable = false)
    private Date deliveryDate; // 배송날짜
    // 타임스탬프는 시간을 더해주거나 빼주는 그런게 따로 없어서
    // Calendar 객체를 사용

    @Column(nullable = false)
    private String deliveryRequest; // 배송요청사항


    @ManyToOne
    private User user;

    @ManyToOne
    private DeliveryAddress deliveryAddress;
}
