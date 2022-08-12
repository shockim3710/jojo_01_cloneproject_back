package com.cloneproject.ssgjojo.recentSearches.dto;

import com.cloneproject.ssgjojo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecentSearchesAddDto {
    private String histories; // 최근검색어

    private Long user;
}
