package com.cloneproject.ssgjojo.user.controller;

import com.cloneproject.ssgjojo.jwt.JwtTokenProvider;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.*;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import com.cloneproject.ssgjojo.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public String addUser(@RequestBody UserSignupDto userSignupDto) {

        return iUserService.addUser(userSignupDto);
    }

    @PostMapping("/user/signupid")
    public String getUserSignUpId(@RequestBody UserSignupDto userSignupDto) {

        return iUserService.getUserSignUpId(userSignupDto);
    }

//    @PostMapping("/user/kakaosignup") // 카카오 회원가입
//    public User addUser(@RequestBody UserKakaoSignupDto userKakaoSignupDto) {
//
//        return iUserService.addKakaoUser(userKakaoSignupDto);
//    }

    @GetMapping("/user/get") // 마이페이지
    public UserGetIdDto getUser(HttpServletRequest request) {
        return iUserService.getUserById(request);
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> getUserLogin(@RequestBody UserLoginDto userLoginDto) {

        String token = iUserService.getUserLogin(userLoginDto);

        if(token  == null) {
            return ResponseEntity.status(401).body("Invalid Password");
        }

        return ResponseEntity.ok(token);

    }


//    @GetMapping("/user/getAll") // 전체 회원 조회
//    public List<UserEditGetAllDto> getAll() {
//        return iUserService.getAll();
//    }

    @PutMapping("/user/edit") // 회원 정보 수정
    public String editUser(@RequestBody UserEditGetAllDto userEditDto, HttpServletRequest request) {
        return iUserService.editUser(userEditDto, request);
    }

    @PutMapping("/user/delete") // 회원 탈퇴
    public String deleteUser(HttpServletRequest request) {
        return iUserService.deleteUser(request);
    }

}