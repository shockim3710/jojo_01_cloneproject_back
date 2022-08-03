package com.cloneproject.ssgjojo.user.controller;

import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.UserDtoInput;
import com.cloneproject.ssgjojo.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final IUserService iUserService;

    @PostMapping("/user/signup") // 회원가입
    public User addUser(@RequestBody UserDtoInput userDtoInput) {

        return iUserService.addUser(userDtoInput);
    }

    @GetMapping("/user/get/{id}") // 마이페이지
    public User getUser(@PathVariable Long id) {
        return iUserService.getUserById(id);
    }

    @GetMapping("/user/getAll") // 회원 조회
    public List<User> getAll() {

        return iUserService.getAll();
    }

    @DeleteMapping("/user/delete/{id}") // 회원 탈퇴
    public void deleteUser(@PathVariable Long id) {
        iUserService.deleteUser(id);
    }

    @PutMapping("/user/edit") // 회원 정보 수정
    public User editUser(@RequestBody User user) {
        return iUserService.editUser(user);
    }

}
