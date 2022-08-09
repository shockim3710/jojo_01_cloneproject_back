package com.cloneproject.ssgjojo.loginHistory.repository;

import com.cloneproject.ssgjojo.loginHistory.domain.LogInHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogInReposotory extends JpaRepository<LogInHistory, Long> {
}
