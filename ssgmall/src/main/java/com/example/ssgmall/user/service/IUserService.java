package com.example.ssgmall.user.service;

import com.example.ssgmall.user.domain.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);
    User getUserById(Long id);
    User editUser(User user);
    List<User> getAll();
    void deleteUser(Long id);
}
