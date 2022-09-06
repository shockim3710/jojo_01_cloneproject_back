package com.cloneproject.ssgjojo.categorylv4.service;

import com.cloneproject.ssgjojo.categorylv4.domain.CategoryLv4;
import com.cloneproject.ssgjojo.categorylv4.dto.CategoryLv4Dto;

import java.util.List;

public interface ICategoryLv4Service {
    CategoryLv4 addCategory(CategoryLv4Dto categoryLv4Dto);
    CategoryLv4 editCategory(CategoryLv4Dto categoryLv4Dto);
    void deleteCategory(Long id);
}
