package com.cloneproject.ssgjojo.category.service;

import com.cloneproject.ssgjojo.category.domain.CategoryL;
import com.cloneproject.ssgjojo.category.domain.CategoryXl;

import java.util.List;

public interface ICategoryLService {

    CategoryL addCategory(CategoryL categoryL);
    CategoryL getCategoryById(Long id);
    CategoryL editCategory(CategoryL categoryL);
    List<CategoryL> getAll();

    void deleteCategory(Long id);
}
