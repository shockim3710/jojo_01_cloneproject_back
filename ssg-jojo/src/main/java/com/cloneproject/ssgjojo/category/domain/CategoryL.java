package com.cloneproject.ssgjojo.category.domain;

import javax.persistence.*;

@Entity
@Table(name = "l_category")
public class CategoryL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String lName;
    private String lImgPath;
}
