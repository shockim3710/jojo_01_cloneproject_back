package com.cloneproject.ssgjojo.categoryLv4.service;

import com.cloneproject.ssgjojo.categoryLv4.domain.CategoryLv4;
import com.cloneproject.ssgjojo.categoryLv4.dto.CategoryLv4Dto;

import java.util.List;

public interface ICategoryLv4Service {
    CategoryLv4 addCategory(CategoryLv4Dto categoryLv4Dto);
    CategoryLv4 getCategoryById(Long id);
    CategoryLv4 editCategory(CategoryLv4Dto categoryLv4Dto);
    List<CategoryLv4> getAllCategory();

    void deleteCategory(Long id);
}
