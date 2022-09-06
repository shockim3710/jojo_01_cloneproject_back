package com.cloneproject.ssgjojo.ordersproductlist.domain;

import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.productoption.domain.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int count; // 하나의 상품을 몇개 주문하는지

    @Column(nullable = false, name = "is_refund")
    private boolean whetherRefund; // 환불여부

    @ManyToOne
    private Product product;

    @ManyToOne
    private ProductOption productOption;

    @ManyToOne
    private Orders orders;
}
