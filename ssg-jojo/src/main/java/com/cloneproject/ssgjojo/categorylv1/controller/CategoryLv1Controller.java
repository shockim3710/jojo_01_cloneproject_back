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

    // XL 카테고리 추가
    @PostMapping("/category/Lv1/add")
    public CategoryLv1 addCategory(@RequestBody CategoryLv1 categoryLv1) {
        return iCategoryLv1Service.addCategory(categoryLv1);
    }

    // XL 카테고리 이미지 추가
    @PostMapping("/category/Lv1/addnew")
    public CategoryLv1 addCategoryWithImage(@RequestParam("categoryImg") MultipartFile img,
                                   @RequestPart String categoryLv1Name) {

        return iCategoryLv1Service.addCategoryWithImg(img, categoryLv1Name);
    }

    // XL 카테고리, L 카테고리 리스트 동시 호출
    @GetMapping("/category/Lv1/findAll")
    public List<CategoryLv1Dto> findAllCategory() {
        return iCategoryLv1Service.findAllCategory();
    }

    // id 통해서 XL 카테고리 찾기
    @GetMapping("/category/Lv1/{id}")
    public CategoryLv1 getCategoryById(@PathVariable Long id) {
        return iCategoryLv1Service.getCategoryById(id);
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
