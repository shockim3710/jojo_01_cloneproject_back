package com.cloneproject.ssgjojo.loginhistory.controller;

import com.cloneproject.ssgjojo.loginhistory.dto.LogInHistoryOutputDto;
import com.cloneproject.ssgjojo.loginhistory.service.ILogInHistoryService;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LogInHistoryController {

    private final ILogInHistoryService iLogInHistoryService;
    private final IUserRepository iUserRepository;

    // 해당 유저의 로그인 기록 조회
    @GetMapping("/loginhistory")
    public List<LogInHistoryOutputDto> getHistoryByUserId(HttpServletRequest request) {
        return iLogInHistoryService.getHistoryByUserId(request);
    }

}
