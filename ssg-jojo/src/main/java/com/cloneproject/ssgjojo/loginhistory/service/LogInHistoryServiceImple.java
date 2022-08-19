package com.cloneproject.ssgjojo.loginhistory.service;

import com.cloneproject.ssgjojo.loginhistory.domain.LogInHistory;
import com.cloneproject.ssgjojo.loginhistory.dto.LogInHistoryDto;
import com.cloneproject.ssgjojo.loginhistory.dto.LogInHistoryOutputDto;
import com.cloneproject.ssgjojo.loginhistory.repository.ILogInHistoryRepository;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LogInHistoryServiceImple implements ILogInHistoryService {

    private final ILogInHistoryRepository iLogInHistoryRepository;
    private final IUserRepository iUserRepository;

    @Override
    public LogInHistoryOutputDto addLogInHistory(LogInHistoryDto logInHistoryDto) {

        Optional<User> user = iUserRepository.findById(logInHistoryDto.getUserId());

        if(user.isPresent()) {
            LogInHistory logInHistory = iLogInHistoryRepository.save(LogInHistory.builder()
                    .logInTime(new Timestamp(System.currentTimeMillis()))
                    .logInIp(logInHistoryDto.getLogInIp())
                    .user(user.get())
                    .build());

            return LogInHistoryOutputDto.builder()
                    .id(logInHistory.getId())
                    .logInIp(logInHistory.getLogInIp())
                    .userId(logInHistory.getUser().getId())
                    .logInTime(logInHistory.getLogInTime())
                    .build();

        }

        return null;
    }

    @Override
    public List<LogInHistoryOutputDto> getHistoryByUserId(Long id) {

        Optional<User> user = iUserRepository.findById(id);

        if(user.isPresent()) {

            List<LogInHistory> logInHistoryList = iLogInHistoryRepository.findAllByUser(user.get());
            List<LogInHistoryOutputDto> logInHistoryOutputDtoList = new ArrayList<>();

            if(!logInHistoryList.isEmpty()) {
                for(LogInHistory logInHistory : logInHistoryList) {
                    logInHistoryOutputDtoList.add(LogInHistoryOutputDto.builder()
                            .id(logInHistory.getId())
                            .logInIp(logInHistory.getLogInIp())
                            .logInTime(logInHistory.getLogInTime())
                            .userId(logInHistory.getUser().getId())
                            .build());
                }
            }

            return logInHistoryOutputDtoList;

        }

        return null;
    }

    @Override
    public List<LogInHistory> getAllLogInHistory() {
        return iLogInHistoryRepository.findAll();
    }
}
