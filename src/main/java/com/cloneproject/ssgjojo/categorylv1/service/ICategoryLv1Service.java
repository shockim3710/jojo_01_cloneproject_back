package com.cloneproject.ssgjojo.categorylv1.service;

import com.cloneproject.ssgjojo.categorylv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categorylv1.dto.CategoryLv1Dto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICategoryLv1Service {

    CategoryLv1 addCategory(MultipartFile categoryImg, String categoryLv1Name);
    CategoryLv1 editCategory(CategoryLv1 categoryLv1);
    List<CategoryLv1Dto> findAllCategory();
    void deleteCategory(Long id);

}
