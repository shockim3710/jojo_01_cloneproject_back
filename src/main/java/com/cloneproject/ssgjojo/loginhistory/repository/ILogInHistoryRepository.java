package com.cloneproject.ssgjojo.loginhistory.repository;

import com.cloneproject.ssgjojo.loginhistory.domain.LogInHistory;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILogInHistoryRepository extends JpaRepository<LogInHistory, Long> {

    List<LogInHistory> findAllByUser(User user);
}
