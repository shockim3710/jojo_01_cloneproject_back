package com.cloneproject.ssgjojo.category.service;

import com.cloneproject.ssgjojo.category.domain.CategoryL;
import com.cloneproject.ssgjojo.category.dto.CategoryLDto;

import java.util.List;

public interface ICategoryLService {

    CategoryL addCategory(CategoryLDto categoryLDto);
//    CategoryLDto getCategoryById(Long id);
    CategoryL editCategory(CategoryLDto categoryLDto);
    List<CategoryL> getAllCategory();

    void deleteCategory(Long id);
}
