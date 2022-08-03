package com.cloneproject.ssgjojo.category.service;

import com.cloneproject.ssgjojo.category.domain.CategoryL;

import java.util.List;

public interface ICategoryLService {

    CategoryL addCategory(CategoryL categoryL);
    CategoryL getCategoryById(Long id);
    CategoryL editCategory(CategoryL categoryL);
    List<CategoryL> getAllCategory();

    void deleteCategory(Long id);
}
