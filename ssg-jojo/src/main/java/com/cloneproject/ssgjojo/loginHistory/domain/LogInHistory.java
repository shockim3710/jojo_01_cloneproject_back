package com.cloneproject.ssgjojo.loginHistory.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogInHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp logInTime;    // 로그인한 시간

    @Column(nullable = false)
    private String logInIp;         // 로그인한 PC, 모바일 (ip)
}
