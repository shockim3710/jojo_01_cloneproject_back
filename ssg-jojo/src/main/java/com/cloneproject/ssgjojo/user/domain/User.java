package com.cloneproject.ssgjojo.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity // DB가 해당 객체를 인식 가능
@DynamicInsert

public class User {

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

    @ColumnDefault("'Membership'")
    private String isMembership; // 비회원 여부

    private String leaveDate; // 탈퇴 날짜

    private String joinDate; // 가입 날짜

    @ColumnDefault("'Friends'")
    private String membershipLevel; // 회원 등급

    @Column(nullable = false)
    private String gender; // 성별
}
