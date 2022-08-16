package com.cloneproject.ssgjojo.recentsearches.repository;

import com.cloneproject.ssgjojo.recentsearches.domain.RecentSearches;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRecentSearchesRepository extends JpaRepository<RecentSearches, Long> {

    List<RecentSearches> findAllByUser(User user);


}
