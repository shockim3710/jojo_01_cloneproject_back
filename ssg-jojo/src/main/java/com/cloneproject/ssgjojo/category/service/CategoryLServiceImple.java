package com.cloneproject.ssgjojo.category.service;

import com.cloneproject.ssgjojo.category.domain.CategoryL;
import com.cloneproject.ssgjojo.category.repository.ICategoryRepositoryL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryLServiceImple implements ICategoryLService {

    private final ICategoryRepositoryL iCategoryRepositoryL;

    @Override
    public CategoryL addCategory(CategoryL categoryL) {
        return iCategoryRepositoryL.save(categoryL);
    }

    @Override
    public CategoryL getCategoryById(Long id) {
        return iCategoryRepositoryL.findById(id).get();
    }

    @Override
    public CategoryL editCategory(CategoryL categoryL) {
        return iCategoryRepositoryL.save(categoryL);
    }

    @Override
    public List<CategoryL> getAllCategory() {
        log.info("getAll category");
        return iCategoryRepositoryL.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        iCategoryRepositoryL.deleteById(id);
    }
}
