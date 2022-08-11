package com.cloneproject.ssgjojo.productoption.domain;

import com.cloneproject.ssgjojo.product.domain.Product;
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
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_option1_name")
    private String productOption1Name;
    @Column(name = "product_option1_contents")
    private String productOption1Contents;

    @Column(name = "product_option2_name")
    private String productOption2Name;
    @Column(name = "product_option2_contents")
    private String productOption2Contents;

    private int stock;

    @ManyToOne
    private Product product;

}
