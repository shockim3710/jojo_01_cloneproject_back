package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.*;

import javax.transaction.Transactional;
import java.util.List;

public interface IUserService {

    User addUser(UseSignupDto useSignupDto);
    UserGetIdDto getUserById(Long id);
    User editUser(UserEditGetAllDto userEditGetAllDto);
    List<UserEditGetAllDto> getAll();

    User deleteUser(Long id);
}
