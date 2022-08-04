package com.cloneproject.ssgjojo.categoryLv3.service;

import com.cloneproject.ssgjojo.categoryLv3.domain.CategoryLv3;
import com.cloneproject.ssgjojo.categoryLv3.dto.CategoryLv3Dto;
import com.cloneproject.ssgjojo.categoryLv2.repository.ICategoryLv2Repository;
import com.cloneproject.ssgjojo.categoryLv3.repository.ICategoryLv3Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryLv3ServiceImple implements ICategoryLv3Service {

    private final ICategoryLv3Repository iCategoryLv3Repository;
    private final ICategoryLv2Repository iCategoryLv2Repository;


    @Override
    public CategoryLv3 addCategory(CategoryLv3Dto categoryLv3Dto) {

//      return iCategoryRepositoryM.save(categoryMDto);
        return iCategoryLv3Repository.save(CategoryLv3.builder()
                .lv3name(categoryLv3Dto.getLv3name())
                .categoryLv2(iCategoryLv2Repository.findById(categoryLv3Dto.getCategoryLv2()).get())
                .build());
    }

    @Override
    public CategoryLv3 getCategoryById(Long id) {
        return iCategoryLv3Repository.findById(id).get();
    }

    @Override
    public CategoryLv3 editCategory(CategoryLv3Dto categoryLv3Dto) {

//      return iCategoryRepositoryM.save(categoryM);
        return iCategoryLv3Repository.save(CategoryLv3.builder()
                .lv3name(categoryLv3Dto.getLv3name())
                .categoryLv2(iCategoryLv2Repository.findById(categoryLv3Dto.getCategoryLv2()).get())
                .build());
    }

    @Override
    public List<CategoryLv3> getAllCategory() {
        return iCategoryLv3Repository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        iCategoryLv3Repository.deleteById(id);
    }
}
