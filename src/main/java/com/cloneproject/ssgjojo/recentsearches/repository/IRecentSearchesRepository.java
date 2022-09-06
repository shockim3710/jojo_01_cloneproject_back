package com.cloneproject.ssgjojo.recentsearches.repository;

import com.cloneproject.ssgjojo.recentsearches.domain.RecentSearches;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IRecentSearchesRepository extends JpaRepository<RecentSearches, Long> {

    Optional<RecentSearches> findByUserIdAndHistories(Long userId, String histories);
    List<RecentSearches> findAllByUser(User user);

    void deleteByHistories(String histories);

    @Query(value = "select rs.histories from RecentSearches rs where rs.user.id=:id ")
    List<String> findTop10ById(@Param("id") Long id, Pageable pageable);

    void deleteByHistoriesAndUser(String histories, User user);
}
