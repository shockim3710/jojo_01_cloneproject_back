package com.studybusan.mvc.study.user.model;

import javax.persistence.*;

@Entity
@Table(name = "user_tb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

//    @Column(name = "user_name", nullable = false, length = 10)
    private String name;
    private String age;
}
