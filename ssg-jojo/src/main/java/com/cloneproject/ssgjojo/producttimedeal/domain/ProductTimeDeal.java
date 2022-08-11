package com.cloneproject.ssgjojo.producttimedeal.domain;

import com.cloneproject.ssgjojo.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTimeDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String timeDealName;
    @Column(nullable = false)
    private Timestamp timeDealStartDate;
    @Column(nullable = false)
    private Timestamp timeDealEndDate;
    private Long price;

    @ManyToOne
    private Product product;

}
