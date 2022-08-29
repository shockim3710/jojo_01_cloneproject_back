package com.cloneproject.ssgjojo.categorylv3.service;

import com.cloneproject.ssgjojo.categorylv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categorylv3.domain.CategoryLv3;
import com.cloneproject.ssgjojo.categorylv3.dto.CategoryLv3Dto;
import com.cloneproject.ssgjojo.categorylv2.repository.ICategoryLv2Repository;
import com.cloneproject.ssgjojo.categorylv3.repository.ICategoryLv3Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryLv3ServiceImple implements ICategoryLv3Service {

    private final ICategoryLv3Repository iCategoryLv3Repository;
    private final ICategoryLv2Repository iCategoryLv2Repository;


    // m 카테고리 추가
    @Override
    public CategoryLv3 addCategory(CategoryLv3Dto categoryLv3Dto) {

        Optional<CategoryLv2> temp = iCategoryLv2Repository.findById(categoryLv3Dto.getCategoryLv2());
        if(temp.isPresent()) {
            return iCategoryLv3Repository.save(CategoryLv3.builder()
                    .lv3name(categoryLv3Dto.getLv3name())
                    .categoryLv2(iCategoryLv2Repository.findById(categoryLv3Dto.getCategoryLv2()).get())
                    .build());
        }

        return null;
    }

    // m 카테고리 수정
    @Override
    public CategoryLv3 editCategory(CategoryLv3 categoryLv3) {
        Optional<CategoryLv3> temp = iCategoryLv3Repository.findById(categoryLv3.getId());
        Optional<CategoryLv2> categoryLv2 = iCategoryLv2Repository.findById(categoryLv3.getCategoryLv2().getId());

        if(categoryLv2.isPresent() && temp.isPresent()) {
            return iCategoryLv3Repository.save(CategoryLv3.builder()
                    .id(categoryLv3.getId())
                    .lv3name(categoryLv3.getLv3name())
                    .categoryLv2(iCategoryLv2Repository.findById(categoryLv3.getCategoryLv2().getId()).get())
                    .build());
        }

        return null;
    }

    // m 카테고리 삭제
    @Override
    public void deleteCategory(Long id) {
        Optional<CategoryLv3> temp = iCategoryLv3Repository.findById(id);
        if(temp.isPresent()) {
            iCategoryLv3Repository.deleteById(id);
        }
    }
}
