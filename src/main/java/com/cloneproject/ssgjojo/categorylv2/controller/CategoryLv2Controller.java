package com.cloneproject.ssgjojo.categorylv2.controller;

import com.cloneproject.ssgjojo.categorylv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categorylv2.dto.CategoryLv2Dto;
import com.cloneproject.ssgjojo.categorylv2.service.ICategoryLv2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryLv2Controller {

    private final ICategoryLv2Service iCategoryLv2Service;

    // l 카테고리 추가
    @PostMapping("/category/Lv2/add")
    public CategoryLv2 addCategory(@RequestBody CategoryLv2Dto categoryLv2Dto) {

        return iCategoryLv2Service.addCategory(categoryLv2Dto);
    }

    // l 카테고리 수정
    @PutMapping("/category/Lv2/edit")
    public CategoryLv2 editCategory(@RequestBody CategoryLv2Dto categoryLv2Dto) {
        return iCategoryLv2Service.editCategory(categoryLv2Dto);
    }

    // l 카테고리 삭제
    @DeleteMapping("/category/Lv2/{id}")
    public void deleteCategory(@PathVariable Long id) {
        iCategoryLv2Service.deleteCategory(id);
    }


}
