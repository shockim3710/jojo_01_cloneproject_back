package com.cloneproject.ssgjojo.loginhistory.controller;

import com.cloneproject.ssgjojo.loginhistory.dto.LogInHistoryOutputDto;
import com.cloneproject.ssgjojo.loginhistory.service.ILogInHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LogInHistoryController {

    private final ILogInHistoryService iLogInHistoryService;

    // 해당 유저의 로그인 기록 조회
    @GetMapping("/loginhistory")
    public ResponseEntity<?> getHistoryByUserId(HttpServletRequest request) {
        List<LogInHistoryOutputDto> logInHistory = iLogInHistoryService.getHistoryByUserId(request);

        if(logInHistory!=null){
            return ResponseEntity.status(200).body(logInHistory);
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

}
