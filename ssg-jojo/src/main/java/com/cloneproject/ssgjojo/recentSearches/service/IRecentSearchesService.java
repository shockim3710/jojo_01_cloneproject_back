package com.cloneproject.ssgjojo.recentSearches.service;

import com.cloneproject.ssgjojo.recentSearches.dto.RecentSearchesAddDto;
import com.cloneproject.ssgjojo.recentSearches.dto.RecentSearchesDto;

import java.util.List;

public interface IRecentSearchesService {

    RecentSearchesAddDto addRecentSearches(RecentSearchesAddDto recentSearchesAddDto);
    List<RecentSearchesDto> getRecentSearchesByUserId(Long id);
    void deleteRecentSearches(Long id);

}
