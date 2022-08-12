package com.cloneproject.ssgjojo.categoryLv4.domain;

// S Category

import com.cloneproject.ssgjojo.categoryLv3.domain.CategoryLv3;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryLv4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lv4name;

    @ManyToOne
    private CategoryLv3 categoryLv3;
}
