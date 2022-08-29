package com.cloneproject.ssgjojo.user.domain;

import com.cloneproject.ssgjojo.util.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity // DB가 해당 객체를 인식 가능
@DynamicUpdate
public class User extends BaseTimeEntity implements UserDetails {
    @Id // 대표값을 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동생성 어노테이션
    private Long id; // 기본키

    @Column(nullable = false)
    private String userId; // 아이디

    //    @Column(nullable = false)
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

    @Column(nullable = false)
    private String membershipLevel; // 회원 등급

    @Column(nullable = false)
    private Boolean isLeave; // 탈퇴 여부

    @Column(nullable = false, name = "is_sns_sign_up")
    private Boolean whetherSnsSignUp; // SNS 이용한 가입여부

    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Role userRole = getRole();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
