package com.cloneproject.ssgjojo.category.domain;

import javax.persistence.*;

@Entity
@Table(name = "s_category")
public class CategoryS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String sName;
    private String sImgPath;
}
