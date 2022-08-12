package com.cloneproject.ssgjojo.loginHistory.service;

import com.cloneproject.ssgjojo.loginHistory.domain.LogInHistory;
import com.cloneproject.ssgjojo.loginHistory.dto.LogInHistoryDto;

import java.util.List;

public interface ILogInHistoryService {

    LogInHistory addLogInHistory(LogInHistoryDto logInHistoryDto);
    List<LogInHistory> getHistoryByUserId(Long id);
    List<LogInHistory> getAllLogInHistory();
}
