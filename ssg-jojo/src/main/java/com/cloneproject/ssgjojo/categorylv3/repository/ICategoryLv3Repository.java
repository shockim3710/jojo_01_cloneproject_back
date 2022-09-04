package com.cloneproject.ssgjojo.categorylv3.repository;

import com.cloneproject.ssgjojo.categorylv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categorylv1.dto.CategoryDto;
import com.cloneproject.ssgjojo.categorylv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categorylv3.domain.CategoryLv3;
import com.cloneproject.ssgjojo.categorylv3.dto.CategoryLv3Dto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;

public interface ICategoryLv3Repository extends JpaRepository<CategoryLv3, Long> {
    List<CategoryLv3> findAllByCategoryLv2(CategoryLv2 categoryLv2);

    @Query(value = "select new com.cloneproject.ssgjojo.categorylv1.dto.CategoryDto(c3.id, 3, c3.lv3name) " +
                    "from CategoryLv3 c3 where c3.categoryLv2.id = (select tmp.categoryLv2.id from CategoryLv3 tmp where tmp.id = :id) ")
    List<CategoryDto> getCategoryLv3(@Param("id") Long id);

    @Query(value = "select new com.cloneproject.ssgjojo.categorylv1.dto.CategoryDto(c2.id, 2, c2.lv2name) " +
            " from CategoryLv2 c2 " +
            " where c2.categoryLv1.id = ( " +
            " select cl2.categoryLv1.id from CategoryLv2 cl2, CategoryLv3 cl3" +
            " where cl2.id = cl3.categoryLv2.id" +
            " and cl3.id = :id " +
            " ) ")
    List<CategoryDto> getParentCategoryLv3(@Param("id") Long id);

    @Query(value = "select new com.cloneproject.ssgjojo.categorylv1.dto.CategoryDto(c3.id, 3, c3.lv3name) " +
                    "from CategoryLv3 c3 where c3.categoryLv2.id = :id ")
    List<CategoryDto> findAllByCategoryLv2_Id(@Param("id") Long id);
}
