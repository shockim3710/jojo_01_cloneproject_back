package com.cloneproject.ssgjojo.category.service;

import com.cloneproject.ssgjojo.category.domain.CategoryXl;
import com.cloneproject.ssgjojo.category.repository.ICategoryRepositoryXl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryXlServiceImple implements ICategoryXlService{

    private final ICategoryRepositoryXl iCategoryRepositoryXl;

    @Override
    public CategoryXl addCategory(CategoryXl categoryXl) {
        return iCategoryRepositoryXl.save(categoryXl);
    }

    @Override
    public CategoryXl getCategoryById(Long id) {
        return iCategoryRepositoryXl.findById(id).get();
    }

    @Override
    public CategoryXl editCategory(CategoryXl categoryXl) {
        return iCategoryRepositoryXl.save(categoryXl);
    }

    @Override
    public List<CategoryXl> getAllCategory() {
        log.info("getAll category");
        return iCategoryRepositoryXl.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        iCategoryRepositoryXl.deleteById(id);
    }
}
