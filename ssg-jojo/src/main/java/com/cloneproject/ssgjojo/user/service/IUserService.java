package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.UserDtoInput;

import java.util.List;

public interface IUserService {

    User addUser(UserDtoInput userDtoInput);
    User getUserById(Long id);
    User editUser(User user);
    List<User> getAll();

    void deleteUser(Long id);
}
