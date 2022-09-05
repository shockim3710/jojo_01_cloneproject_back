package com.cloneproject.ssgjojo.recentlyproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecentlyProductOutputDto {
    private Long id;
    private Long productId;
    private Long price;
    private String productInfo;
    private String thumb;
}
