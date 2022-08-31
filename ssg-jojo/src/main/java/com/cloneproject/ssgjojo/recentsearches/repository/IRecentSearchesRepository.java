package com.cloneproject.ssgjojo.recentsearches.repository;

import com.cloneproject.ssgjojo.recentsearches.domain.RecentSearches;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IRecentSearchesRepository extends JpaRepository<RecentSearches, Long> {

    List<RecentSearches> findAllByUser(User user);

    @Query(value = "select distinct(rs.histories) from RecentSearches rs where rs.user.id=:id")
    List<String> findTop10(@Param("id") Long id, Pageable pageable);

    void deleteByIdAndUser(Long id, User user);
    void deleteAllByUser(User user);
}
