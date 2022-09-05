package com.cloneproject.ssgjojo.categorylv2.repository;

import com.cloneproject.ssgjojo.categorylv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categorylv1.dto.CategoryDto;
import com.cloneproject.ssgjojo.categorylv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.loginhistory.domain.LogInHistory;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;

public interface ICategoryLv2Repository extends JpaRepository<CategoryLv2, Long> {
    List<CategoryLv2> findAllByCategoryLv1(CategoryLv1 categoryLv1);

    @Query(value = "select new com.cloneproject.ssgjojo.categorylv1.dto.CategoryDto(c1.id, 1, c1.lv1name) " +
            " from CategoryLv1 c1 ")
    List<CategoryDto> getParentCategoryLv2();

    @Query(value = "select new com.cloneproject.ssgjojo.categorylv1.dto.CategoryDto(c2.id, 2, c2.lv2name) " +
            " from CategoryLv2 c2 " +
            " where c2.categoryLv1.id = (select tmp.categoryLv1.id from CategoryLv2 tmp where tmp.id = :id) ")
    List<CategoryDto> getCategoryLv2(@Param("id") Long id);

    @Query(value = "select new com.cloneproject.ssgjojo.categorylv1.dto.CategoryDto(c2.id, 2, c2.lv2name) " +
            "from CategoryLv2 c2 where c2.categoryLv1.id = :id ")
    List<CategoryDto> findAllByCategoryLv1_Id(@Param("id") Long id);
}
