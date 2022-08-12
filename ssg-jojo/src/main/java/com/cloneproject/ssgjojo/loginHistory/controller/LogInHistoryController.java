package com.cloneproject.ssgjojo.loginHistory.controller;

import com.cloneproject.ssgjojo.loginHistory.domain.LogInHistory;
import com.cloneproject.ssgjojo.loginHistory.dto.LogInHistoryDto;
import com.cloneproject.ssgjojo.loginHistory.service.ILogInHistoryService;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LogInHistoryController {

    private final ILogInHistoryService iLogInHistoryService;
    private final IUserRepository iUserRepository;

    @PostMapping("/loginhistory/add")
    public LogInHistory addLogInHistory(@RequestBody LogInHistoryDto logInHistoryDto) {
        return iLogInHistoryService.addLogInHistory(logInHistoryDto);
    }

    @GetMapping("/loginhistory/getAll")
    public List<LogInHistory> getAllLogInHistory() {
        return iLogInHistoryService.getAllLogInHistory();
    }


    @GetMapping("/loginhistory/{id}")
    public List<LogInHistory> getHistoryByUserId(@PathVariable Long id) {
        return iLogInHistoryService.getHistoryByUserId(id);
    }

}
