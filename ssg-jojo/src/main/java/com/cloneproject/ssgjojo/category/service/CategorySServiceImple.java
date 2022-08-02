package com.cloneproject.ssgjojo.category.service;

import com.cloneproject.ssgjojo.category.domain.CategoryS;
import com.cloneproject.ssgjojo.category.repository.ICategoryRepositoryS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategorySServiceImple implements ICategorySService{

    private final ICategoryRepositoryS iCategoryRepositoryS;

    @Override
    public CategoryS addCategory(CategoryS categoryS) {
        return iCategoryRepositoryS.save(categoryS);
    }

    @Override
    public CategoryS getCategoryById(Long id) {
        return iCategoryRepositoryS.findById(id).get();
    }

    @Override
    public CategoryS editCategory(CategoryS categoryS) {
        return iCategoryRepositoryS.save(categoryS);
    }

    @Override
    public List<CategoryS> getAll() {
        return iCategoryRepositoryS.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        iCategoryRepositoryS.deleteById(id);
    }
}
