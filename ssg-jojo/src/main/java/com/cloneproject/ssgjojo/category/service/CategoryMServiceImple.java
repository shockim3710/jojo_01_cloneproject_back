package com.cloneproject.ssgjojo.category.service;

import com.cloneproject.ssgjojo.category.domain.CategoryM;
import com.cloneproject.ssgjojo.category.repository.ICategoryRepositoryM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryMServiceImple implements ICategoryMService {

    private final ICategoryRepositoryM iCategoryRepositoryM;


    @Override
    public CategoryM addCategory(CategoryM categoryM) {
        return iCategoryRepositoryM.save(categoryM);
    }

    @Override
    public CategoryM getCategoryById(Long id) {
        return iCategoryRepositoryM.findById(id).get();
    }

    @Override
    public CategoryM editCategory(CategoryM categoryM) {
        return iCategoryRepositoryM.save(categoryM);
    }

    @Override
    public List<CategoryM> getAll() {
        return iCategoryRepositoryM.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        iCategoryRepositoryM.deleteById(id);
    }
}
