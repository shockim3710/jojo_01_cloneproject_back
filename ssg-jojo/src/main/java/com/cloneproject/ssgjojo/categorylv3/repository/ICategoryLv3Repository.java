package com.cloneproject.ssgjojo.categorylv3.repository;

import com.cloneproject.ssgjojo.categorylv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categorylv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categorylv3.domain.CategoryLv3;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryLv3Repository extends JpaRepository<CategoryLv3, Long> {
    List<CategoryLv3> findAllByCategoryLv2(CategoryLv2 categoryLv2);
}
