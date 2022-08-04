package com.cloneproject.ssgjojo.categoryLv2.service;

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

@Slf4j
@RequiredArgsConstructor
@Service
@Builder
public class CategoryLv2ServiceImple implements ICategoryLv2Service {

    private final ICategoryLv2Repository iCategoryLv2Repository;
    private final ICategoryLv1Repository iCategoryLv1Repository;

    @Override
    public CategoryLv2 addCategory(CategoryLv2Dto categoryLv2Dto) {

//       return iCategoryRepositoryL.save(categoryLDto);

         return iCategoryLv2Repository.save(CategoryLv2.builder()
                 .lv2name(categoryLv2Dto.getLv2name())
                 .categoryLv1(iCategoryLv1Repository.findById(categoryLv2Dto.getCategoryLv1()).get())
                 .build());
    }

//    @Override
//    public CategoryLDto getCategoryById(Long id) {
//
//        CategoryL categoryL = iCategoryRepositoryL.findById(id).get();
//        return CategoryLDto.builder()
//                .id(categoryL.getId())
//                .xlcategoryname(categoryL.getCategoryXl().getxlcategoryname())
////              .xlId((categoryL.getCategoryXl().getId()))
//                .build();
//    }

    @Override
    public CategoryLv2 editCategory(CategoryLv2Dto categoryLv2Dto) {

//      return iCategoryRepositoryL.save(categoryL);
        return iCategoryLv2Repository.save(CategoryLv2.builder()
                .lv2name(categoryLv2Dto.getLv2name())
                .categoryLv1(iCategoryLv1Repository.findById(categoryLv2Dto.getCategoryLv1()).get())
                .build());
    }

    @Override
    public List<CategoryLv2> getAllCategory() {
        log.info("getAll category");
        return iCategoryLv2Repository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        iCategoryLv2Repository.deleteById(id);
    }
}
