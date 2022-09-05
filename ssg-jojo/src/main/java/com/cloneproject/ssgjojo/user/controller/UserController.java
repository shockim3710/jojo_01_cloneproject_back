package com.cloneproject.ssgjojo.user.controller;

import com.cloneproject.ssgjojo.jwt.JwtTokenProvider;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.*;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import com.cloneproject.ssgjojo.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final IUserService iUserService;

    @PostMapping("/user/signup") // 회원가입
    public ResponseEntity<?> addUser(@RequestBody UserSignupDto userSignupDto) {
        User user = iUserService.addUser(userSignupDto);

        if(user!=null){
            return ResponseEntity.status(200).body("환영합니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @PostMapping("/user/signupid") // 아이디 중복확인
    public ResponseEntity<?> getUserSignUpId(@RequestBody UserSignupDto userSignupDto) {

        if(iUserService.getUserSignUpId(userSignupDto)){
            return ResponseEntity.status(200).body("가입 가능");
        }else{
            return ResponseEntity.status(200).body("가입 불가능");
        }
    }

    @GetMapping("/user/get") // 마이페이지
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        UserGetIdDto user = iUserService.getUserById(request);

        if(user!=null){
            return ResponseEntity.status(200).body(user);
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @PostMapping("/user/login") // 로그인
    public ResponseEntity<?> getUserLogin(@RequestBody UserLoginDto userLoginDto) {
        String user = iUserService.getUserLogin(userLoginDto);

        if(user!=null){
            return ResponseEntity.status(200).body(user);
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @PutMapping("/user/edit") // 회원 정보 수정
    public ResponseEntity<?> editUser(@RequestBody UserEditGetAllDto userEditDto, HttpServletRequest request) {
        String user = iUserService.editUser(userEditDto, request);

        if(user!=null){
            return ResponseEntity.status(200).body(user);
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @PutMapping("/user/delete") // 회원 탈퇴
    public ResponseEntity<?> deleteUser(HttpServletRequest request) {
        Optional<User> user = iUserService.deleteUser(request);

        if(user.isPresent()){
            return ResponseEntity.status(200).body("탈퇴하였습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

}