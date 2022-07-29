package com.busan.ssg.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name", nullable = false)
    private String productName;
    private Long price;
    private String descripion;
    private String manufactureCompany;
    private String registrationDate;
    private String recommendations;
    private String discountRate;
    private String fee;
    private String color;
    private String size;
    private String availableStock;
    private String updateDate;






}
