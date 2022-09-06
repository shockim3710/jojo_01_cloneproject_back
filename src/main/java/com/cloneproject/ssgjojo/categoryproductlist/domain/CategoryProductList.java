package com.cloneproject.ssgjojo.categoryproductlist.domain;

import com.cloneproject.ssgjojo.categorylv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categorylv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categorylv3.domain.CategoryLv3;
import com.cloneproject.ssgjojo.categorylv4.domain.CategoryLv4;
import com.cloneproject.ssgjojo.product.domain.Product;
import lombok.*;

import javax.persistence.*;

/**
 * id: 상품 카테고리의 PK
 * categoryLv1: 상품의 카테고리 Lv1 PK
 * categoryLv2: 상품의 카테고리 Lv2 PK
 * categoryLv3: 상품의 카테고리 Lv3 PK
 * categoryLv4: 상품의 카테고리 Lv4 PK
 * product: 상품의 PK
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"categoryLv1","categoryLv2","categoryLv3","categoryLv4","product"})
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
