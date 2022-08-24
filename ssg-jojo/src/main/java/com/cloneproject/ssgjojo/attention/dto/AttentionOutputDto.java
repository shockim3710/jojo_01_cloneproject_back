package com.cloneproject.ssgjojo.attention.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttentionOutputDto {
    private Long productId;
    private String productInfo;
    private Long productPrice;
}
