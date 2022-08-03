package com.cloneproject.ssgjojo.category.service;

import com.cloneproject.ssgjojo.category.domain.CategoryM;
import com.cloneproject.ssgjojo.category.domain.CategoryXl;

import java.util.List;

public interface ICategoryMService {

    CategoryM addCategory(CategoryM categoryM);
    CategoryM getCategoryById(Long id);
    CategoryM editCategory(CategoryM categoryM);
    List<CategoryM> getAllCategory();

    void deleteCategory(Long id);
}
