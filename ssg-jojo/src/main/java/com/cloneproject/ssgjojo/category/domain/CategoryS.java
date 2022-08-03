package com.cloneproject.ssgjojo.category.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "s_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String sName;

//    private CategoryM categoryM;
}
