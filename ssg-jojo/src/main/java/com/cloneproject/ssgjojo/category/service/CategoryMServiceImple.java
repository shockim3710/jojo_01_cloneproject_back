package com.cloneproject.ssgjojo.category.service;

import com.cloneproject.ssgjojo.category.domain.CategoryL;
import com.cloneproject.ssgjojo.category.domain.CategoryM;
import com.cloneproject.ssgjojo.category.dto.CategoryMDto;
import com.cloneproject.ssgjojo.category.repository.ICategoryRepositoryL;
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
    private final ICategoryRepositoryL iCategoryRepositoryL;


    @Override
    public CategoryM addCategory(CategoryMDto categoryMDto) {

//      return iCategoryRepositoryM.save(categoryMDto);
        return iCategoryRepositoryM.save(CategoryM.builder()
                .mcategoryname(categoryMDto.getMcategoryname())
                .categoryL(iCategoryRepositoryL.findById(categoryMDto.getCategoryL()).get())
                .build());
    }

    @Override
    public CategoryM getCategoryById(Long id) {
        return iCategoryRepositoryM.findById(id).get();
    }

    @Override
    public CategoryM editCategory(CategoryMDto categoryMDto) {

//      return iCategoryRepositoryM.save(categoryM);
        return iCategoryRepositoryM.save(CategoryM.builder()
                .mcategoryname(categoryMDto.getMcategoryname())
                .categoryL(iCategoryRepositoryL.findById(categoryMDto.getCategoryL()).get())
                .build());
    }

    @Override
    public List<CategoryM> getAllCategory() {
        return iCategoryRepositoryM.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        iCategoryRepositoryM.deleteById(id);
    }
}
