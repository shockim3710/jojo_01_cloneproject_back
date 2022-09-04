package com.cloneproject.ssgjojo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGetIdDto {

    private String name; // 이름
    private String membershipLevel; // 회원 등급
    private String phone; // 전화번호
    private String email; // 이메일
}
