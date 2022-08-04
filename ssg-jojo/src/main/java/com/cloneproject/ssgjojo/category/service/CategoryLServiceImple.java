package com.cloneproject.ssgjojo.category.service;

import com.cloneproject.ssgjojo.category.domain.CategoryL;
import com.cloneproject.ssgjojo.category.domain.CategoryXl;
import com.cloneproject.ssgjojo.category.dto.CategoryLDto;
import com.cloneproject.ssgjojo.category.repository.ICategoryRepositoryL;
import com.cloneproject.ssgjojo.category.repository.ICategoryRepositoryXl;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Builder
public class CategoryLServiceImple implements ICategoryLService {

    private final ICategoryRepositoryL iCategoryRepositoryL;
    private final ICategoryRepositoryXl iCategoryRepositoryXl;

    @Override
    public CategoryL addCategory(CategoryLDto categoryLDto) {

//       return iCategoryRepositoryL.save(categoryLDto);

         return iCategoryRepositoryL.save(CategoryL.builder()
                 .lcategoryname(categoryLDto.getLcategoryname())
                 .categoryXl(iCategoryRepositoryXl.findById(categoryLDto.getCategoryXl()).get())
                 .build());
    }

//    @Override
//    public CategoryLDto getCategoryById(Long id) {
//
//        CategoryL categoryL = iCategoryRepositoryL.findById(id).get();
//        return CategoryLDto.builder()
//                .id(categoryL.getId())
//                .xlcategoryname(categoryL.getCategoryXl().getXlcategoryname())
////              .xlId((categoryL.getCategoryXl().getId()))
//                .build();
//    }

    @Override
    public CategoryLDto editCategory(CategoryLDto categoryLDto) {

//      return iCategoryRepositoryL.save(categoryL);
        return null;
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
