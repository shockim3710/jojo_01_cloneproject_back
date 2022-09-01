package com.cloneproject.ssgjojo.recentsearches.service;

import com.cloneproject.ssgjojo.recentsearches.dto.RecentSearchesAddDto;
import com.cloneproject.ssgjojo.recentsearches.dto.RecentSearchesDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IRecentSearchesService {

    RecentSearchesAddDto addRecentSearches(RecentSearchesAddDto recentSearchesAddDto, HttpServletRequest request);
    List<RecentSearchesDto> getRecentSearchesByUserId(HttpServletRequest request);
    void deleteRecentSearches(Long id);

}
