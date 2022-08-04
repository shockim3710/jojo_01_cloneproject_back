package com.cloneproject.ssgjojo.category.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "m_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String mcategoryname;

    @ManyToOne
    private CategoryL categoryL;
}
