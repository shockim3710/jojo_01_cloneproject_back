package com.cloneproject.ssgjojo.categorylv4.repository;

import com.cloneproject.ssgjojo.categorylv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categorylv1.dto.CategoryDto;
import com.cloneproject.ssgjojo.categorylv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categorylv3.domain.CategoryLv3;
import com.cloneproject.ssgjojo.categorylv4.domain.CategoryLv4;
import com.cloneproject.ssgjojo.categorylv4.dto.CategoryLv4Dto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryLv4Repository extends JpaRepository<CategoryLv4, Long> {
    List<CategoryLv4> findAllByCategoryLv3(CategoryLv3 categoryLv3);

    @Query(value = "select new com.cloneproject.ssgjojo.categorylv1.dto.CategoryDto(c4.id, 4, c4.lv4name) " +
            "from CategoryLv4 c4 where c4.categoryLv3.id = (select tmp.categoryLv3.id from CategoryLv4 tmp where tmp.id = :id) ")
    List<CategoryDto> getCategoryLv4(@Param("id") Long id);

//    @Query(value = "select c4.id, c4.categoryLv3.id, c4.lv4name from CategoryLv4 c4 where c4.id <> :id " +
//            "and c4.categoryLv3.id = (select tmp.categoryLv3.id from CategoryLv4 tmp where tmp.id = :id) ")
//    List<CategoryLv4Dto> getCategoryLv4(@Param("id") Long id);

    @Query(value = "select new com.cloneproject.ssgjojo.categorylv1.dto.CategoryDto(c4.id, 4, c4.lv4name) " +
            "from CategoryLv4 c4 where c4.categoryLv3.id = :id ")
    List<CategoryDto> findAllByCategoryLv3_Id(@Param("id") Long id);

}
