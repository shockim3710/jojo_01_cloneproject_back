package com.cloneproject.ssgjojo.categoryLv1.service;

import com.cloneproject.ssgjojo.categoryLv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categoryLv1.repository.ICategoryLv1Repository;
import com.cloneproject.ssgjojo.categoryLv1.service.ICategoryLv1Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryLv1ServiceImple implements ICategoryLv1Service {

    private final ICategoryLv1Repository iCategoryLv1Repository;

    @Override
    public CategoryLv1 addCategory(CategoryLv1 categoryLv1) {
        return iCategoryLv1Repository.save(categoryLv1);
    }

    @Override
    public CategoryLv1 getCategoryById(Long id) {
        return iCategoryLv1Repository.findById(id).get();
    }

    @Override
    public CategoryLv1 editCategory(CategoryLv1 categoryLv1) {
        return iCategoryLv1Repository.save(categoryLv1);
    }

    @Override
    public List<CategoryLv1> getAllCategory() {
        return iCategoryLv1Repository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        iCategoryLv1Repository.deleteById(id);
    }
}
