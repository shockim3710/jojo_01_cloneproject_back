package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.UserDtoInput;
import com.cloneproject.ssgjojo.user.dto.UserDtoOutput;

import java.util.List;

public interface IUserService {

    User addUser(UserDtoInput userDtoInput);
    UserDtoOutput getUserById(Long id);
    User editUser(UserDtoOutput userDtoOutput);
    List<UserDtoOutput> getAll();

    User deleteUser(UserDtoInput userDtoInput);
}
