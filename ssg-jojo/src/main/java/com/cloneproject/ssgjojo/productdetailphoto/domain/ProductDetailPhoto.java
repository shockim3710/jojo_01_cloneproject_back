package com.cloneproject.ssgjojo.productdetailphoto.domain;

import com.cloneproject.ssgjojo.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String productDetailPhotoPath;
    @Column(nullable = false)
    private String productDetailPhotoOriginName;
    @Column(nullable = false)
    private int productDetailPhotoSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
