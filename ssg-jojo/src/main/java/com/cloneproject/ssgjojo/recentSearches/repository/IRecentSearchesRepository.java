package com.cloneproject.ssgjojo.recentSearches.repository;

import com.cloneproject.ssgjojo.recentSearches.domain.RecentSearches;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecentSearchesRepository extends JpaRepository<RecentSearches, Long> {


}
