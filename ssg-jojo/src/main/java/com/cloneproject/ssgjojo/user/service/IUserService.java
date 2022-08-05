package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.*;

import java.util.List;

public interface IUserService {

    User addUser(UseSignupDto useSignupDto);
    UserGetIdDto getUserById(Long id);
    User editUser(UserEditDto userEditDto);
    List<UserGetAllDto> getAll();

    User deleteUser(UserEditDto userEditDto);

}
