package com.cloneproject.ssgjojo.recentlyproduct.dto;

import com.cloneproject.ssgjojo.recentlyproduct.domain.RecentlyProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentlyProductDeleteDto {
    private Long id;
    private Long userId;
}
