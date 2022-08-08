package com.cloneproject.ssgjojo.categoryLv2.service;

import com.cloneproject.ssgjojo.categoryLv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categoryLv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categoryLv2.dto.CategoryLv2Dto;
import com.cloneproject.ssgjojo.categoryLv2.repository.ICategoryLv2Repository;
import com.cloneproject.ssgjojo.categoryLv1.repository.ICategoryLv1Repository;
import com.cloneproject.ssgjojo.categoryLv2.service.ICategoryLv2Service;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Builder
public class CategoryLv2ServiceImple implements ICategoryLv2Service {

    private final ICategoryLv2Repository iCategoryLv2Repository;
    private final ICategoryLv1Repository iCategoryLv1Repository;

    @Override
    public CategoryLv2 addCategory(CategoryLv2Dto categoryLv2Dto) {

        Optional<CategoryLv1> temp = iCategoryLv1Repository.findById(categoryLv2Dto.getCategoryLv1());
        if(temp.isPresent()) {
            return iCategoryLv2Repository.save(CategoryLv2.builder()
                    .lv2name(categoryLv2Dto.getLv2name())
                    .categoryLv1(iCategoryLv1Repository.findById(categoryLv2Dto.getCategoryLv1()).get())
                    .build());
        }

        return null;
    }

    @Override
    public CategoryLv2 getCategoryById(Long id) {
        return iCategoryLv2Repository.findById(id).get();
    }

    @Override
    public CategoryLv2 editCategory(CategoryLv2Dto categoryLv2Dto) {

        Optional<CategoryLv1> categoryLv1 = iCategoryLv1Repository.findById(categoryLv2Dto.getCategoryLv1());
        Optional<CategoryLv2> categoryLv2 = iCategoryLv2Repository.findById(categoryLv2Dto.getId());
        if(categoryLv1.isPresent() && categoryLv2.isPresent()) {
            return iCategoryLv2Repository.save(CategoryLv2.builder()
                    .id(categoryLv2Dto.getId())
                    .lv2name(categoryLv2Dto.getLv2name())
                    .categoryLv1(iCategoryLv1Repository.findById(categoryLv2Dto.getCategoryLv1()).get())
                    .build());
        }

        return null;
    }

    @Override
    public List<CategoryLv2> getAllCategory() {
        log.info("getAll category");
        return iCategoryLv2Repository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<CategoryLv2> temp = iCategoryLv2Repository.findById(id);
        if(temp.isPresent()) {
            iCategoryLv2Repository.deleteById(id);
        }
    }
}
