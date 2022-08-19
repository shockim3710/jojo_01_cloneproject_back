package com.cloneproject.ssgjojo.recentsearches.service;

import com.cloneproject.ssgjojo.recentsearches.dto.RecentSearchesAddDto;
import com.cloneproject.ssgjojo.recentsearches.dto.RecentSearchesDto;

import java.util.List;

public interface IRecentSearchesService {

    RecentSearchesAddDto addRecentSearches(RecentSearchesAddDto recentSearchesAddDto);
    List<RecentSearchesDto> getRecentSearchesByUserId(Long id);
    void deleteRecentSearches(Long id);

}
