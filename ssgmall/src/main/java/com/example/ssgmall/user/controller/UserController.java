package com.example.ssgmall.user.controller;

import com.example.ssgmall.user.domain.User;
import com.example.ssgmall.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final IUserService iUserService;

    @PostMapping("/user/add")
    public User addUser(@RequestBody User user) { return iUserService.addUser(user); }

    @GetMapping("/user/getAll")
    public List<User> getAll() { return iUserService.getAll(); }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable Long id) { iUserService.deleteUser(id); }
}
