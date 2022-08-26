package com.cloneproject.ssgjojo.user.controller;

import com.cloneproject.ssgjojo.jwt.JwtTokenProvider;
import com.cloneproject.ssgjojo.user.domain.Role;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.*;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import com.cloneproject.ssgjojo.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final IUserRepository iUserRepository;

//    final String EMAIL = "aabbcc@gmail.com";
//    final Role ROLE = Role.USER;
//
//    final String USERID = "werg";
//    final String PASSW = "234";
//    final String NAME = "weg";
//    final String BIRTH = "1212";
//    final String GENDER = "ee";
//    final String MEMBERSHIP = "sdg";
//    final Boolean ILEAVE = false;
//    final Boolean SNSSIGH = false;
//    final String PHONE = "01012341234";
//
//    User user = User.builder()
//            .userId(USERID)
//            .password(PASSW)
//            .name(NAME)
//            .birth(BIRTH)
//            .email(EMAIL)
//            .gender(GENDER)
//            .membershipLevel(MEMBERSHIP)
//            .isLeave(ILEAVE)
//            .whetherSnsSignUp(SNSSIGH)
//            .phone(PHONE)
//            .role(ROLE)
//            .build();
//
//
//    @PostMapping("/join")
//    public String join(){
//        log.info("로그인 시도됨");
//
//        iUserRepository.save(user);
//
//
//        return user.toString();
//
//    }

    // 로그인
//    @PostMapping("/login")
//    public String login(@RequestBody Map<String, String> user) {
//
//        log.info("아이디 = {}", user.get("userId"));
//
//        User member = iUserRepository.findByUserId(user.get("userId"))
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디 입니다."));
//
//        // if(~~~.matches(user.pwd(), member.getPassword())) => 같으면 토큰 생성
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        if(encoder.matches(inputPw, user.getPassword())) {//넘겨받은 비밀번호와 user객체에 암호화된 비밀번호와 비교
//        }
//
//        return jwtTokenProvider.createToken(member.getId(), member.getRole());
//    }









    private final IUserService iUserService;

    @PostMapping("/user/signup") // 회원가입
    public User addUser(@RequestBody UserSignupDto userSignupDto) {

        return iUserService.addUser(userSignupDto);
    }
//
//    @PostMapping("/user/kakaosignup") // 카카오 회원가입
//    public User addUser(@RequestBody UserKakaoSignupDto userKakaoSignupDto) {
//
//        return iUserService.addKakaoUser(userKakaoSignupDto);
//    }
//
//    @GetMapping("/user/get/{id}") // 마이페이지
//    public UserGetIdDto getUser(@PathVariable Long id) {
//        return iUserService.getUserById(id);
//    }
//
    @PostMapping("/user/login")
    public UserLoginDto getUserLogin(@RequestBody UserLoginDto userLoginDto) {

        return iUserService.getUserLogin(userLoginDto);
    }
//
//
//    @GetMapping("/user/getAll") // 회원 조회
//    public List<UserEditGetAllDto> getAll() {
//        return iUserService.getAll();
//    }
//
//    @PutMapping("/user/edit") // 회원 정보 수정
//    public User editUser(@RequestBody UserEditGetAllDto userEditDto) {
//        return iUserService.editUser(userEditDto);
//    }
//
////    @PutMapping("/user/delete") // 회원 탈퇴
////    public void deleteUser(@PathVariable UserEditDto userEditDto) {
////        iUserService.deleteUser(userEditDto);
////    }
//
//    @PutMapping("/user/delete/{id}") // 회원 탈퇴
//    public User deleteUser(@PathVariable Long id) {
//        return iUserService.deleteUser(id);
//    }

}