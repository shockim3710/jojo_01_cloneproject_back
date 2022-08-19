package com.cloneproject.ssgjojo.loginhistory.service;

import com.cloneproject.ssgjojo.loginhistory.domain.LogInHistory;
import com.cloneproject.ssgjojo.loginhistory.dto.LogInHistoryDto;

import java.util.List;

public interface ILogInHistoryService {

    LogInHistory addLogInHistory(LogInHistoryDto logInHistoryDto);
    List<LogInHistory> getHistoryByUserId(Long id);
    List<LogInHistory> getAllLogInHistory();
}
