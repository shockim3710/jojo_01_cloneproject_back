package com.cloneproject.ssgjojo.loginhistory.service;

import com.cloneproject.ssgjojo.loginhistory.dto.LogInHistoryOutputDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ILogInHistoryService {

    List<LogInHistoryOutputDto> getHistoryByUserId(HttpServletRequest request);
}
