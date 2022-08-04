package com.cloneproject.ssgjojo.category.service;

import com.cloneproject.ssgjojo.category.domain.CategoryM;
import com.cloneproject.ssgjojo.category.domain.CategoryS;
import com.cloneproject.ssgjojo.category.dto.CategorySDto;
import com.cloneproject.ssgjojo.category.repository.ICategoryRepositoryM;
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
    private final ICategoryRepositoryM iCategoryRepositoryM;

    @Override
    public CategoryS addCategory(CategorySDto categorySDto) {

//      return iCategoryRepositoryS.save(categoryS);
        return iCategoryRepositoryS.save(CategoryS.builder()
                .scategoryname(categorySDto.getScategoryname())
                .categoryM(iCategoryRepositoryM.findById(categorySDto.getCategoryM()).get())
                .build());
    }

    @Override
    public CategoryS getCategoryById(Long id) {
        return iCategoryRepositoryS.findById(id).get();
    }

    @Override
    public CategoryS editCategory(CategorySDto categorySDto) {

//      return iCategoryRepositoryS.save(categoryS);
        return iCategoryRepositoryS.save(CategoryS.builder()
                .scategoryname(categorySDto.getScategoryname())
                .categoryM(iCategoryRepositoryM.findById(categorySDto.getCategoryM()).get())
                .build());
    }

    @Override
    public List<CategoryS> getAllCategory() {
        return iCategoryRepositoryS.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        iCategoryRepositoryS.deleteById(id);
    }
}
