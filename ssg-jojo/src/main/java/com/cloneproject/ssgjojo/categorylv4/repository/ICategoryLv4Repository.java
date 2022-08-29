package com.cloneproject.ssgjojo.categorylv4.repository;

import com.cloneproject.ssgjojo.categorylv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categorylv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categorylv3.domain.CategoryLv3;
import com.cloneproject.ssgjojo.categorylv4.domain.CategoryLv4;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryLv4Repository extends JpaRepository<CategoryLv4, Long> {
    List<CategoryLv4> findAllByCategoryLv3(CategoryLv3 categoryLv3);

}
