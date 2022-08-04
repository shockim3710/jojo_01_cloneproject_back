package com.cloneproject.ssgjojo.category.service;

import com.cloneproject.ssgjojo.category.domain.CategoryM;
import com.cloneproject.ssgjojo.category.domain.CategoryS;
import com.cloneproject.ssgjojo.category.domain.CategoryXl;
import com.cloneproject.ssgjojo.category.dto.CategorySDto;

import java.util.List;

public interface ICategorySService {
    CategoryS addCategory(CategorySDto categorySDto);
    CategoryS getCategoryById(Long id);
    CategoryS editCategory(CategorySDto categorySDto);
    List<CategoryS> getAllCategory();

    void deleteCategory(Long id);
}
