package com.cloneproject.ssgjojo.category.service;

import com.cloneproject.ssgjojo.category.domain.CategoryM;
import com.cloneproject.ssgjojo.category.domain.CategoryXl;
import com.cloneproject.ssgjojo.category.dto.CategoryMDto;

import java.util.List;

public interface ICategoryMService {

    CategoryM addCategory(CategoryMDto categoryMDto);
    CategoryM getCategoryById(Long id);
    CategoryM editCategory(CategoryMDto categoryMDto);
    List<CategoryM> getAllCategory();

    void deleteCategory(Long id);
}
