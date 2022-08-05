package com.cloneproject.ssgjojo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignupDto {

//    private Long id; // 기본키
    private String userId; // 아이디
    private String password; // 비밀번호
    private String name; // 이름
    private String birth; // 생년월일
    private String phone; // 전화번호
    private String email; // 이메일
    private String gender; // 성별
    private String membershipLevel; // 회원 등급
    private Boolean isLeave; // 탈퇴 여부
}
