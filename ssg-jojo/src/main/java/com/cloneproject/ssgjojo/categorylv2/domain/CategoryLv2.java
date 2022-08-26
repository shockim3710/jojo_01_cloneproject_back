
// L Category

package com.cloneproject.ssgjojo.categorylv2.domain;

import com.cloneproject.ssgjojo.categorylv1.domain.CategoryLv1;
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
public class CategoryLv2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lv2name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_lv1_id")
    private CategoryLv1 categoryLv1;
}
