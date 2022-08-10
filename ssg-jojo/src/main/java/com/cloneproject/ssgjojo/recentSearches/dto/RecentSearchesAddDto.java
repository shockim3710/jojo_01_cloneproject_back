package com.cloneproject.ssgjojo.recentSearches.dto;

import com.cloneproject.ssgjojo.user.domain.User;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

public class RecentSearchesAddDto {
    private String histories; // 최근검색어
    private Long user;

}
