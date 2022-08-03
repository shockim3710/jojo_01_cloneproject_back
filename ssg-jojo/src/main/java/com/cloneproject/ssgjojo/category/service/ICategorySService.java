package com.cloneproject.ssgjojo.category.service;

import com.cloneproject.ssgjojo.category.domain.CategoryM;
import com.cloneproject.ssgjojo.category.domain.CategoryS;
import com.cloneproject.ssgjojo.category.domain.CategoryXl;

import java.util.List;

public interface ICategorySService {
    CategoryS addCategory(CategoryS categoryS);
    CategoryS getCategoryById(Long id);
    CategoryS editCategory(CategoryS categoryS);
    List<CategoryS> getAllCategory();

    void deleteCategory(Long id);
}
