package com.cloneproject.ssgjojo.attention.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * AttentionDeleteDto: 전체 폴더에서 좋아요 삭제할 때 사용되는 Dto
 * productId: 상품 PK
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttentionDeleteDto {
    private List<Long> productId;
}
