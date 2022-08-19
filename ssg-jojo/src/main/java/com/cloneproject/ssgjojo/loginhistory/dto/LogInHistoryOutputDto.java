package com.cloneproject.ssgjojo.loginhistory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogInHistoryOutputDto {

    private Long id;
    private String logInIp;
    private Long userId;
    private Timestamp logInTime;

}
