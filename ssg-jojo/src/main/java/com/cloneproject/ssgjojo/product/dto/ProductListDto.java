package com.cloneproject.ssgjojo.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductListDto {
    /**
     * id : 상품아이디
     * thumbnailUri : 썸네일 이미지 경로
     * mallName : 몰 이름
     * productName : 상품 이름
     * manufactureCompany : 제조사
     * discountRate : 할인율
     * oldPrice : 할인 전 가격
     * newPrice : 할인 적용 가격 (할인 없을 경우에도 newPrice에 저장)
     * reviewScore : 상품 리뷰 점수
     * reviewNum : 상품 리뷰 개수
     * fee : 배송비
     * adultCase : 성인 판매 여부
     * attention : 좋아요 여부
     */
    private long id;
    private String thumbnailUri;
    private String mallName;
    private String productName;
    private String manufactureCompany;
    private int discountRate;
    private long oldPrice;
    private long newPrice;
    private double reviewScore;
    private long reviewNum;
    private int fee;
    private boolean adultCase;
    private boolean attention;

    public ProductListDto(long id, String thumbnailUri, String mallName,
                          String productName, String manufactureCompany,
                          int discountRate, long oldPrice, long newPrice,
                          int fee, boolean adultCase,
                          long reviewNum, double reviewScore, boolean attention) {
        this.id = id;
        this.thumbnailUri = thumbnailUri;
        this.mallName = mallName;
        this.productName = productName;
        this.manufactureCompany = manufactureCompany;
        this.discountRate = discountRate;
        this.oldPrice = discountRate != 0 ? oldPrice : 0;
        this.newPrice = discountRate != 0 ? newPrice * (1 - (discountRate / 100)) : newPrice;
        this.reviewScore = reviewScore;
        this.reviewNum = reviewNum;
        this.fee = fee;
        this.adultCase = adultCase;
        this.attention = attention;
    }
}
