package com.cloneproject.ssgjojo.recentsearches.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecentSearchesDto {
    private Long id;
    private String histories; // 최근검색어

    private Long user;
}
