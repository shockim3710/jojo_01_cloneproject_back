package com.busan.ssg.user.repository;

import com.busan.ssg.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {


}
