package com.cloneproject.ssgjojo.categoryLv2.service;

import com.cloneproject.ssgjojo.categoryLv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categoryLv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categoryLv2.dto.CategoryLv2Dto;

import java.util.List;

public interface ICategoryLv2Service {

    CategoryLv2 addCategory(CategoryLv2Dto categoryLv2Dto);
    CategoryLv2 getCategoryById(Long id);
    CategoryLv2 editCategory(CategoryLv2Dto categoryLv2Dto);
    List<CategoryLv2> getAllCategory();

    void deleteCategory(Long id);
}
