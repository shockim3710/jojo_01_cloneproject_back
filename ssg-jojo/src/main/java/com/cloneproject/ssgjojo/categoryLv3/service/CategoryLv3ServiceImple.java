package com.cloneproject.ssgjojo.categoryLv3.service;

import com.cloneproject.ssgjojo.categoryLv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categoryLv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categoryLv3.domain.CategoryLv3;
import com.cloneproject.ssgjojo.categoryLv3.dto.CategoryLv3Dto;
import com.cloneproject.ssgjojo.categoryLv2.repository.ICategoryLv2Repository;
import com.cloneproject.ssgjojo.categoryLv3.repository.ICategoryLv3Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryLv3ServiceImple implements ICategoryLv3Service {

    private final ICategoryLv3Repository iCategoryLv3Repository;
    private final ICategoryLv2Repository iCategoryLv2Repository;


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

    @Override
    public CategoryLv3 getCategoryById(Long id) {
        return iCategoryLv3Repository.findById(id).get();
    }

    @Override
    public CategoryLv3 editCategory(CategoryLv3Dto categoryLv3Dto) {
        Optional<CategoryLv3> categoryLv3 = iCategoryLv3Repository.findById(categoryLv3Dto.getId());
        Optional<CategoryLv2> categoryLv2 = iCategoryLv2Repository.findById(categoryLv3Dto.getCategoryLv2());

        if(categoryLv2.isPresent() && categoryLv3.isPresent()) {
            return iCategoryLv3Repository.save(CategoryLv3.builder()
                    .id(categoryLv3Dto.getId())
                    .lv3name(categoryLv3Dto.getLv3name())
                    .categoryLv2(iCategoryLv2Repository.findById(categoryLv3Dto.getCategoryLv2()).get())
                    .build());
        }

        return null;
    }

    @Override
    public List<CategoryLv3> getAllCategory() {
        return iCategoryLv3Repository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<CategoryLv3> temp = iCategoryLv3Repository.findById(id);
        if(temp.isPresent()) {
            iCategoryLv3Repository.deleteById(id);
        }
    }
}
