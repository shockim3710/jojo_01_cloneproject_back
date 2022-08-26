package com.cloneproject.ssgjojo.loginhistory.service;

import com.cloneproject.ssgjojo.loginhistory.domain.LogInHistory;
import com.cloneproject.ssgjojo.loginhistory.dto.LogInHistoryDto;
import com.cloneproject.ssgjojo.loginhistory.dto.LogInHistoryOutputDto;

import java.util.List;

public interface ILogInHistoryService {

    LogInHistoryOutputDto addLogInHistory(LogInHistoryDto logInHistoryDto);
    List<LogInHistoryOutputDto> getHistoryByUserId(Long id);
//    List<LogInHistory> getAllLogInHistory();
}
