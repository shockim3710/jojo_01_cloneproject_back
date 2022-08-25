package com.cloneproject.ssgjojo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDto {

    private Long id; // 기본키
    private String userId; // 아이디
    private String password; // 비밀번호
    private String name; // 이름


}
