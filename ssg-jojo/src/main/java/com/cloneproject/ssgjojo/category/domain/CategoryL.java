package com.cloneproject.ssgjojo.category.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "l_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String lName;

//    private CategoryXl categoryXl;
}
