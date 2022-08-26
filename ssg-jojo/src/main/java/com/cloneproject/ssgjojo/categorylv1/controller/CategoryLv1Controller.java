package com.cloneproject.ssgjojo.categorylv1.controller;

import com.cloneproject.ssgjojo.categorylv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categorylv1.dto.CategoryLv1Dto;
import com.cloneproject.ssgjojo.categorylv1.service.ICategoryLv1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryLv1Controller {

    private final ICategoryLv1Service iCategoryLv1Service;


    // XL 카테고리 추가 (이미지 포함)
    @PostMapping("/category/Lv1/addnew")
    public CategoryLv1 addCategory(@RequestParam("categoryImg") MultipartFile img,
                                   @RequestPart String categoryLv1Name) {

        return iCategoryLv1Service.addCategory(img, categoryLv1Name);
    }

    // XL 카테고리, L 카테고리 리스트 동시 호출
    @GetMapping("/category/Lv1/findAll")
    public List<CategoryLv1Dto> findAllCategory() {
        return iCategoryLv1Service.findAllCategory();
    }

    // XL 카테고리 수정
    @PutMapping("/category/Lv1/edit")
    public CategoryLv1 editCategory(@RequestBody CategoryLv1 categoryLv1) {
        return iCategoryLv1Service.editCategory(categoryLv1);
    }

    // XL 카테고리 삭제
    @DeleteMapping("/category/Lv1/{id}")
    public void deleteCategory(@PathVariable Long id) {
        iCategoryLv1Service.deleteCategory(id);
    }

}
