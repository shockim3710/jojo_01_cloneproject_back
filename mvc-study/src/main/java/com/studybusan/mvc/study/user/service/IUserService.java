package com.studybusan.mvc.study.user.service;

import com.studybusan.mvc.study.user.model.User;

import java.util.List;

public interface IUserService {

    User addUser(User user);
    List<User> getAll();
}
