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
public class UserSignupDto {

    private String userId; // 아이디
    private String password; // 비밀번호
    private String name; // 이름
    private String birth; // 생년월일
    private String phone; // 전화번호
    private String email; // 이메일
    private String gender; // 성별
    private String membershipLevel; // 회원 등급
    private Boolean isLeave; // 탈퇴 여부
    private Boolean whetherSnsSignUp; // SNS 이용한 가입여부
    private Role role;

    private String address; // 배송지
    private boolean whetherDefaultAddress; // 기본배송지 여부
    private boolean whetherOnlyThisTime; // 이번만배송지 여부
    private String addressName; // 주소별칭
    private String receiveName; // 받는분
    private String zipCode; // 우편번호

    private String folderName;
}
