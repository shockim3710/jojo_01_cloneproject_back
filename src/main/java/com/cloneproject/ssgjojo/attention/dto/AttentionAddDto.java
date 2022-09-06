package com.cloneproject.ssgjojo.attention.dto;

import com.cloneproject.ssgjojo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * AttentionAddDto: 좋아요 추가할 때 사용되는 Dto
 * productId: 상품 PK
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttentionAddDto {
    private Long productId;
}
