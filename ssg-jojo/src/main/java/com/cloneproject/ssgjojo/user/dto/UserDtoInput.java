package com.cloneproject.ssgjojo.user.dto;

import lombok.Data;

@Data
public class UserDtoInput {

    private String userId; // 아이디
    private String password; // 비밀번호
    private String name; // 이름
    private String birth; // 생년월일
    private String phone; // 전화번호
    private String email; // 이메일
    private String gender; // 성별
    private String membershipLevel; // 회원 등급
    private String joinDate; // 가입 날짜
//    private String leaveDate; // 탈퇴 날짜



}
