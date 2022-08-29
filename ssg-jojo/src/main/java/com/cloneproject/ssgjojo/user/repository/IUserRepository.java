package com.cloneproject.ssgjojo.user.repository;

import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId); // 사용자 아이디
    Optional<User> findById(Long id); // 사용자 기본키

}
