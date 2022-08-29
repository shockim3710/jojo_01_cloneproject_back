package com.cloneproject.ssgjojo.user.dto;

import com.cloneproject.ssgjojo.user.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDto {

    private String userId; // 아이디
    private String password; // 비밀번호

    private String logInIp;
}
