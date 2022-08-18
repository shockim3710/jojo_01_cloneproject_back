package com.cloneproject.ssgjojo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEditGetAllDto {

    private Long id; // 기본키
    private String userId; // 아이디
    private String password; // 비밀번호 (회원 정보 수정)
    private String name; // 이름 (회원 정보 수정)
    private String birth; // 생년월일 (회원 정보 수정)
    private String phone; // 전화번호 (회원 정보 수정)
    private String email; // 이메일 (회원 정보 수정)
    private String gender; // 성별 (회원 정보 수정)
    private String membershipLevel; // 회원 등급
    private Boolean isLeave; // 탈퇴 여부 (회원 탈퇴)
    private Timestamp createTime;
}
