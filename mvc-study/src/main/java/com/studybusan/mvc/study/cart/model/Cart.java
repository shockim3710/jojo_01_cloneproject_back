package com.studybusan.mvc.study.cart.model;

import com.studybusan.mvc.study.product.model.Product;
import com.studybusan.mvc.study.user.model.User;
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
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int qty;

    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;


}
