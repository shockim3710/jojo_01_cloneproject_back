package com.cloneproject.ssgjojo.categorylv2.repository;

import com.cloneproject.ssgjojo.categorylv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categorylv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.loginhistory.domain.LogInHistory;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryLv2Repository extends JpaRepository<CategoryLv2, Long> {
    List<CategoryLv2> findAllByCategoryLv1(CategoryLv1 categoryLv1);
}
