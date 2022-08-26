package com.cloneproject.ssgjojo.user.repository;

import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

    User findByUserIdAndPassword(String userId, String password);
    User findByUserId(String userId);



}
