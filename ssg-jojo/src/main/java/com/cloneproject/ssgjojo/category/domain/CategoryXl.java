package com.cloneproject.ssgjojo.category.domain;

import javax.persistence.*;

@Entity
@Table(name = "xl_category")
public class CategoryXl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String xlName;
    private String xlImgPath;
}
