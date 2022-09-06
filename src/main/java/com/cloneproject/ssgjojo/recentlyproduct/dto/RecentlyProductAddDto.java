package com.cloneproject.ssgjojo.recentlyproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentlyProductAddDto {
    private Long userId;
    private Long productId;
}
