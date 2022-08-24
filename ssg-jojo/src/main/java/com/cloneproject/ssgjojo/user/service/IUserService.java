package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.*;

import java.util.List;

public interface IUserService {

    User addUser(UserSignupDto userSignupDto);
    UserGetIdDto getUserById(Long id);

    UserLoginDto getUserLogin(UserLoginDto userLoginDto);

    User editUser(UserEditGetAllDto userEditGetAllDto);
    List<UserEditGetAllDto> getAll();

    User deleteUser(Long id);
}
