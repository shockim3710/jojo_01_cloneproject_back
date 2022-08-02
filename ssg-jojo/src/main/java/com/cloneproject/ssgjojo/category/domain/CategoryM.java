package com.cloneproject.ssgjojo.category.domain;

import javax.persistence.*;

@Entity
@Table(name = "m_category")
public class CategoryM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String mName;
    private String mImgPath;
}
