
// M Category

package com.cloneproject.ssgjojo.categorylv3.domain;

import com.cloneproject.ssgjojo.categorylv2.domain.CategoryLv2;
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
public class CategoryLv3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lv3name;

    @ManyToOne
    private CategoryLv2 categoryLv2;
}
