package com.cloneproject.ssgjojo.product.domain;

import com.cloneproject.ssgjojo.util.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 * id : Product PK
 * price : 가격
 * description : 상세정보
 * productName : 상품 이름
 * manufactureCompany : 제조사
 * discountRate : 할인율
 * fee : 배송비
 * color : 색상
 * size : 사이즈
 * availableStcok : 재고
 * createdDate    : 등록일
 * modifiedDate   : 수정일
 */


// DynamicUpdate 어노테이션 추가하고
// @DynamicUpdate
// 더티체킹 하면 상태가 변경된 것만 업데이트 함.
// 더티체킹하면 JPA 컨텍스트 안에 들어가있는거만 ...
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String manufactureCompany;
    @Column(nullable = false)
    private int discountRate;
    @Column(nullable = false)
    private int fee;
    @Column(name = "is_adult_case", nullable = false)
    private boolean adultCase;

}