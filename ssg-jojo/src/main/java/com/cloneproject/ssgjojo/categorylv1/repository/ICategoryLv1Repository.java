package com.cloneproject.ssgjojo.categorylv1.repository;

import com.cloneproject.ssgjojo.categorylv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categorylv1.dto.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryLv1Repository extends JpaRepository<CategoryLv1,Long> {

    @Query(value = "select new com.cloneproject.ssgjojo.categorylv1.dto.CategoryDto(c1.id, 1, c1.lv1name)" +
            " from CategoryLv1 c1 ")
    List<CategoryDto> getCategoryLv1(@Param("id") Long id);
}
