package com.cloneproject.ssgjojo.qna.domain;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@DynamicUpdate
@Builder
@Entity //
@AllArgsConstructor
@NoArgsConstructor
public class QnA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String questionMain;

    private String answerMain;
    private Timestamp questionDate;
    private Timestamp answerDate;

    @Column(name = "is_lock", nullable = false)
    private boolean lockCase;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

}
