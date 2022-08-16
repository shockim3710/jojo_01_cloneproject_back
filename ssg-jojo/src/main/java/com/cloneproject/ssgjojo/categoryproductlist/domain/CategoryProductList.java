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

    @ManyToOne
    private CategoryLv1 categoryLv1;
    @ManyToOne
    private CategoryLv2 categoryLv2;
    @ManyToOne
    private CategoryLv3 categoryLv3;
    @ManyToOne
    private CategoryLv4 categoryLv4;

    @ManyToOne
    private Product product;


}
