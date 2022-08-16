package com.cloneproject.ssgjojo.categoryProductList.domain;

import com.cloneproject.ssgjojo.categorylv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categorylv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categorylv3.domain.CategoryLv3;
import com.cloneproject.ssgjojo.categorylv4.domain.CategoryLv4;
import com.cloneproject.ssgjojo.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_lv1_id")
    private CategoryLv1 categoryLv1;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_lv2_id")
    private CategoryLv2 categoryLv2;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_lv3_id")
    private CategoryLv3 categoryLv3;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_lv4_id")
    private CategoryLv4 categoryLv4;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


}
