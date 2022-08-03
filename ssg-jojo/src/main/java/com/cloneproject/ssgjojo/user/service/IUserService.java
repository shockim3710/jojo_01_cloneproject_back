package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.user.domain.User;

import java.util.List;

public interface IUserService {

    User addUser(User user);
    User getUserById(Long id);
    User editUser(User user);
    List<User> getAll();

    void deleteUser(Long id);
}
