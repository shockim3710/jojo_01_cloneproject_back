package com.cloneproject.ssgjojo.user.domain;

import com.cloneproject.ssgjojo.util.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity // DB가 해당 객체를 인식 가능

public class User extends BaseTimeEntity {

    @Id // 대표값을 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동생성 어노테이션
    private Long id; // 기본키

    @Column(nullable = false)
    private String userId; // 아이디

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    private String name; // 이름

    @Column(nullable = false)
    private String birth; // 생년월일

    @Column(nullable = false)
    private String phone; // 전화번호

    @Column(nullable = false)
    private String email; // 이메일

    @Column(nullable = false)
    private String gender; // 성별

    private String membershipLevel; // 회원 등급

    private Boolean isLeave; // 탈퇴 여부
}
