package com.cloneproject.ssgjojo.category.service;

import com.cloneproject.ssgjojo.category.domain.CategoryXl;

import java.util.List;

public interface ICategoryXlService {

    CategoryXl addCategory(CategoryXl categoryXl);
    CategoryXl getCategoryById(Long id);
    CategoryXl editCategory(CategoryXl categoryXl);
    List<CategoryXl> getAll();

    void deleteCategory(Long id);

}
