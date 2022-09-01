package com.cloneproject.ssgjojo.loginhistory.service;

import com.cloneproject.ssgjojo.jwt.JwtTokenProvider;
import com.cloneproject.ssgjojo.loginhistory.domain.LogInHistory;
import com.cloneproject.ssgjojo.loginhistory.dto.LogInHistoryOutputDto;
import com.cloneproject.ssgjojo.loginhistory.repository.ILogInHistoryRepository;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LogInHistoryServiceImple implements ILogInHistoryService {

    private final ILogInHistoryRepository iLogInHistoryRepository;
    private final IUserRepository iUserRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 해당 유저의 로그인 기록 조회
    @Override
    public List<LogInHistoryOutputDto> getHistoryByUserId(HttpServletRequest request) {

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);

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

}
