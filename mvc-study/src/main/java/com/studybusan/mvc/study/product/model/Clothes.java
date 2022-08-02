package com.studybusan.mvc.study.product.model;

import javax.persistence.Entity;
// import javax.persistence.MappedSuperclass;

@Entity
// @MappedSuperclass
// 공통 mapping 정보 필요할 때
public class Clothes extends Product {

    private String size;
    private String color;
}
