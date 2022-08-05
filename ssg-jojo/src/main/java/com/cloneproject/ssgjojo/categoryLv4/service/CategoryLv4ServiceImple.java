package com.cloneproject.ssgjojo.categoryLv4.service;

import com.cloneproject.ssgjojo.categoryLv4.domain.CategoryLv4;
import com.cloneproject.ssgjojo.categoryLv4.dto.CategoryLv4Dto;
import com.cloneproject.ssgjojo.categoryLv3.repository.ICategoryLv3Repository;
import com.cloneproject.ssgjojo.categoryLv4.repository.ICategoryLv4Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryLv4ServiceImple implements ICategoryLv4Service {

    private final ICategoryLv4Repository iCategoryLv4Repository;
    private final ICategoryLv3Repository iCategoryLv3Repository;

    @Override
    public CategoryLv4 addCategory(CategoryLv4Dto categoryLv4Dto) {

//      return iCategoryRepositoryS.save(categoryS);
        return iCategoryLv4Repository.save(CategoryLv4.builder()
                .lv4name(categoryLv4Dto.getLv4name())
                .categoryLv3(iCategoryLv3Repository.findById(categoryLv4Dto.getCategoryLv3()).get())
                .build());
    }

    @Override
    public CategoryLv4 getCategoryById(Long id) {
        return iCategoryLv4Repository.findById(id).get();
    }

    @Override
    public CategoryLv4 editCategory(CategoryLv4Dto categoryLv4Dto) {

//      return iCategoryRepositoryS.save(categoryS);
        return iCategoryLv4Repository.save(CategoryLv4.builder()
                .id(categoryLv4Dto.getId())
                .lv4name(categoryLv4Dto.getLv4name())
                .categoryLv3(iCategoryLv3Repository.findById(categoryLv4Dto.getCategoryLv3()).get())
                .build());
    }

    @Override
    public List<CategoryLv4> getAllCategory() {
        return iCategoryLv4Repository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        iCategoryLv4Repository.deleteById(id);
    }
}
