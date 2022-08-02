package com.studybusan.mvc.study.user.repository;

import com.studybusan.mvc.study.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository <User, Long>{
}
