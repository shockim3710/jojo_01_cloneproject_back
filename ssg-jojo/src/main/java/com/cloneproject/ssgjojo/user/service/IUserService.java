package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.*;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User addUser(UseSignupDto useSignupDto);
    UserGetIdDto getUserById(Long id);
    User editUser(UserEditGetAllDto userEditGetAllDto);
    List<UserEditGetAllDto> getAll();

    User deleteUser(Long id);
}
