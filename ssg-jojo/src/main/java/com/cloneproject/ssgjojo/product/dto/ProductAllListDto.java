package com.cloneproject.ssgjojo.product.dto;

public class ProductAllListDto {
    /**
     * id : 상품아이디
     * thumbnailUri : 썸네일 이미지 경로
     * mallName : 몰 이름
     * manufactureCompany : 제조사
     * productName : 상품 이름
     * discountRate : 할인율
     * oldPrice : 할인 전 가격
     * newPrice : 할인 적용 가격 (할인 없을 경우에도 newPrice에 저장)
     * reviewScore : 상품 리뷰 점수
     * reviewNum : 상품 리뷰 개수
     * fee : 배송비
     */
    private Long id;
    private String thumbnailUri;
    private String mallName;
    private String manufactureCompany;
    private String productName;
    private float discountRate;
    private Long oldPrice;
    private Long newPrice;
    private float reviewScore;
    private float reviewNum;
    private Long fee;

}
