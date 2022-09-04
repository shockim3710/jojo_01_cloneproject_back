package com.cloneproject.ssgjojo.recentsearches.service;

import com.cloneproject.ssgjojo.recentsearches.domain.RecentSearches;
import com.cloneproject.ssgjojo.recentsearches.dto.RecentSearchesAddDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface IRecentSearchesService {
    RecentSearches addRecentSearches(RecentSearchesAddDto recentSearchesAddDto, HttpServletRequest request);
    List<String> getRecentSearchesByUserId(HttpServletRequest request);
    Optional<RecentSearches> deleteRecentSearches(Long id, HttpServletRequest request);
    void deleteAllByUser(HttpServletRequest request);
}
