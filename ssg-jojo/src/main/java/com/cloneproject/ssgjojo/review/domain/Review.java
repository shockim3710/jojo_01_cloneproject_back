package com.cloneproject.ssgjojo.review.domain;

import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.util.BaseTimeEntity;
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
// BaseTimeEntity : 생성날짜, 수정날짜 추가할 때
public class Review  extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;      // 리뷰 제목

    @Column(nullable = false)
    private String mainText;   // 본문

    @Column(nullable = false)
    private int score;         // 별점

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Orders orders;
}
