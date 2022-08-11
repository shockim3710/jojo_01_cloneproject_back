package com.cloneproject.ssgjojo.loginHistory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogInHistoryDto {

    private String logInIp;

    private Long userId;
}
