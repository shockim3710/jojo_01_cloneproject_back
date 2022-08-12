package com.cloneproject.ssgjojo.loginHistory.service;

import com.cloneproject.ssgjojo.loginHistory.domain.LogInHistory;
import com.cloneproject.ssgjojo.loginHistory.dto.LogInHistoryDto;
import com.cloneproject.ssgjojo.loginHistory.repository.ILogInHistoryRepository;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LogInHistoryServiceImple implements ILogInHistoryService {

    private final ILogInHistoryRepository iLogInHistoryRepository;
    private final IUserRepository iUserRepository;

    @Override
    public LogInHistory addLogInHistory(LogInHistoryDto logInHistoryDto) {

        Optional<User> user = iUserRepository.findById(logInHistoryDto.getUserId());

        if(user.isPresent()) {
            return iLogInHistoryRepository.save(LogInHistory.builder()
                    .logInTime(new Timestamp(System.currentTimeMillis()))
                    .logInIp(logInHistoryDto.getLogInIp())
                    .user(user.get())
                    .build());
        }

        return null;
    }

    @Override
    public List<LogInHistory> getHistoryByUserId(Long id) {

        Optional<User> user = iUserRepository.findById(id);

        if(user.isPresent()) {
            return iLogInHistoryRepository.findAllByUser(user.get());
        }

        return null;
    }

    @Override
    public List<LogInHistory> getAllLogInHistory() {
        return iLogInHistoryRepository.findAll();
    }
}
