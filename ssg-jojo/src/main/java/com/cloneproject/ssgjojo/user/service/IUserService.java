package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.*;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {

    String addUser(UserSignupDto userSignupDto);
//    User addKakaoUser(UserKakaoSignupDto userKakaoSignupDto);
//
    UserGetIdDto getUserById(HttpServletRequest request);
//
    String getUserLogin(UserLoginDto userLoginDto);
//
    String editUser(UserEditGetAllDto userEditDto, HttpServletRequest request);

    //    List<UserEditGetAllDto> getAll();
//
    String deleteUser(HttpServletRequest request);
}
