package com.cloneproject.ssgjojo.categorylv1.service;

import com.cloneproject.ssgjojo.categorylv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categorylv1.dto.CategoryLv1Dto;

import java.util.List;

public interface ICategoryLv1Service {

    CategoryLv1 addCategory(CategoryLv1 categoryLv1);
    CategoryLv1 getCategoryById(Long id);
    CategoryLv1 editCategory(CategoryLv1 categoryLv1);
    List<CategoryLv1> getAllCategory();
    List<CategoryLv1Dto> findAllCategory();
    void deleteCategory(Long id);

}
