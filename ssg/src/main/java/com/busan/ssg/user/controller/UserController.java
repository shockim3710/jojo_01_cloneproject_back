package com.busan.ssg.user.controller;

import com.busan.ssg.user.domain.User;
import com.busan.ssg.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final IUserService iUserService;

    @PostMapping("/user/add")
    public User addUser(@RequestBody User user) {

        return iUserService.addUser(user);
    }

    @GetMapping("/user/get/{id}")
    public User getUser(@PathVariable Long id) {
        return iUserService.getUserById(id);
    }

    @GetMapping("/user/getAll")
    public List<User> getAll() {

        return iUserService.getAll();
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        iUserService.deleteUser(id);
    }

    @PutMapping("/user/edit")
    public User editUser(@RequestBody User user) {
        return iUserService.editUser(user);
    }

}
