package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface IUserService {
    User addUser(UserSignupDto userSignupDto);
    boolean getUserSignUpId(UserSignupDto userSignupDto);
    UserGetIdDto getUserById(HttpServletRequest request);
    String getUserLogin(UserLoginDto userLoginDto);
    String editUser(UserEditGetAllDto userEditDto, HttpServletRequest request);
    Optional<User> deleteUser(HttpServletRequest request);
}
