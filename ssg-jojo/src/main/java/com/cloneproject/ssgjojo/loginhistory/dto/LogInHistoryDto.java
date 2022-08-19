package com.cloneproject.ssgjojo.loginhistory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogInHistoryDto {

    private String logInIp;

    private Long userId;
}
